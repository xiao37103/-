import numpy as np
from sqlalchemy import create_engine, text

# 连接数据库
engine = create_engine('mysql+pymysql://root:123456@127.0.0.1/ge?charset=utf8')
conn = engine.connect()
for i in ['合肥市', '芜湖市', '蚌埠市', '淮南市', '马鞍山市', '淮北市', '铜陵市', '安庆市', '黄山市', '滁州市',
          '阜阳市', '宿州市', '亳州市', '池州市', '宣城市']:
    result = conn.execute(text(f"SELECT location FROM {i}"))
    data = list(result)
    arr = []
    for row in data:
        arr.append(list(map(float, row[0].split(','))))
    coords = np.array(arr)

    num_particles = 50
    # 惯性因子
    w = 0.5
    # 学习因子
    c1 = 1
    c2 = 1


    def fitness_function(coords, particle):
        count = 0
        for coord in coords:
            distance = np.sqrt(np.sum((coord - particle) ** 2))
            if distance <= 0.05:  # 设定半径为三公里，换算成经纬度大概是0.05
                count += 1
        return count


    # 粒子群算法迭代更新
    def pso(coords, num_particles, w, c1, c2, max_iterations):
        # 初始化粒子位置和速度
        particles = np.random.uniform(low=coords.min(axis=0), high=coords.max(axis=0), size=(num_particles, 2))
        velocities = np.zeros_like(particles)

        # 初始化全局最优位置和适应度函数值
        global_best_particle = particles[0]
        global_best_fitness = fitness_function(coords, global_best_particle)

        # 迭代更新
        best_particle_data = []
        for i in range(max_iterations):
            # 更新每个粒子的速度和位置
            for j in range(num_particles):
                # 计算惯性项
                inertia = w * velocities[j]

                # 计算认知项和社会项
                cognitive = c1 * np.random.uniform() * (particles[j] - particles[j])
                social = c2 * np.random.uniform() * (global_best_particle - particles[j])

                # 更新粒子速度和位置
                velocities[j] = inertia + cognitive + social
                particles[j] += velocities[j]

                # 约束粒子位置在经纬度数据范围内
                particles[j] = np.minimum(coords.max(axis=0), np.maximum(coords.min(axis=0), particles[j]))

                # 计算粒子适应度函数值
                fitness = fitness_function(coords, particles[j])

                # 更新粒子个体最优位置和全局最优位置
                if fitness > fitness_function(coords, global_best_particle):
                    global_best_particle = particles[j]
                    global_best_fitness = fitness

            # 输出当前迭代次数和全局最优适应度函数值
            print('Iteration: {}, Global Best Fitness: {}'.format(i, global_best_fitness))

            # 记录每个粒子的经纬度数据和适应度函数值
            particle_data = []
            for j in range(num_particles):
                particle_data.append(np.concatenate((particles[j], [fitness_function(coords, particles[j])])))
            best_particle_data.append(particle_data)

        return best_particle_data, global_best_particle


    # 运行PSO算法，得到所有粒子的经纬度数据和适应度函数值以及最优经纬度数据
    all_particles_data, best_particle = pso(coords, num_particles, w, c1, c2, max_iterations=10)
    print('Best Particle: {}'.format(best_particle))
    import pandas as pd

    # 将 PSO 算法得到的结果以 DataFrame 的形式展示
    dfs = []


    def normalize(x):
        return (x - x_min) / (x_max - x_min)


    for particle_data in all_particles_data:
        df = pd.DataFrame(particle_data, columns=['longitude', 'latitude', 'fitness'])
        x_min = df['fitness'].min()
        x_max = df['fitness'].max()
        df['comfort_norm'] = df['fitness'].apply(normalize)
        dfs.append(df)
    print(dfs)
    from sqlalchemy import create_engine

    # 连接数据库
    engine = create_engine('mysql+pymysql://root:123456@127.0.0.1/ge?charset=utf8')

    # 将 DataFrame 写入 MySQL 数据库
    combined_df = pd.concat(dfs, ignore_index=True)
    combined_df.to_sql(name=f'{i}充电桩', con=engine, index=False, if_exists='replace')

    print('数据已追加到 MySQL 数据库')

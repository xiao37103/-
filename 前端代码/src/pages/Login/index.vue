<template>
    <div class="bgimg">
        <div class="container right-panel-active">
            <!-- 注册 -->
            <div class="container__form container--signup">
                <el-form :rules="rule" action="#" class="form" id="form1">
                    <h2 class="form__title">注册</h2>
                    <input type="text" placeholder="User" class="input" v-model="registeruser" />
                    <!-- <input type="email" placeholder="email  " class="input" /> -->
                    <input
                        type="password"
                        placeholder="Password"
                        class="input"
                        v-model="registerpassword"
                    />
                    <button class="btn" @click="finishregister">完成</button>
                </el-form>
            </div>

            <!-- 登录 -->
            <div class="container__form container--signin">
                <el-form :rules="rule" action="#" class="form" id="form2">
                    <h2 class="form__title">登录</h2>
                    <input type="text" placeholder="User" class="input" v-model="loginuser" />
                    <input
                        type="password"
                        placeholder="Password"
                        class="input"
                        v-model="loginpassword"
                    />
                    <el-button
                        class="btn"
                        @click="finishlogin"
                        v-loading.fullscreen.lock="fullscreenLoading"
                    >进入</el-button>
                </el-form>
            </div>

            <!-- Overlay -->
            <div class="container__overlay">
                <div class="overlay">
                    <div class="overlay__panel overlay--left">
                        <button class="btn" id="signIn" @click="handlesignin">登录</button>
                    </div>
                    <div class="overlay__panel overlay--right">
                        <button class="btn" id="signUp" @click="handlesignup">注册</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script >
export default {
    name: "Login",
    data() {
        return {
            fullscreenLoading: false,
            loginuser: "admin",
            loginpassword: "123456",
            registeruser: "admin",
            registerpassword: "123456",
            rule: {
                username: [
                    {
                        required: true,
                        message: "请输入用户名",
                        trigger: "blur",
                    },
                ],
                password: [
                    { required: true, message: "请输入密码", trigger: "blur" },
                ],
            },
        };
    },
    methods: {
        handlesignup: function () {
            const signUpBtn = document.getElementById("signUp");
            const container = document.querySelector(".container");
            signUpBtn.addEventListener("click", () => {
                container.classList.add("right-panel-active");
            });
        },
        handlesignin: function () {
            const signInBtn = document.getElementById("signIn");
            const container = document.querySelector(".container");
            signInBtn.addEventListener("click", () => {
                container.classList.remove("right-panel-active");
            });

        },
        finishlogin: function (payload) {
            if (
                this.loginuser === localStorage["username"] &&
                this.loginpassword === localStorage["password"]
            ) {
                this.fullscreenLoading = true;
                setTimeout(() => {
                    this.fullscreenLoading = false;
                    this.$router.replace("/Souye");
                }, 750);
                
                 //如果输入的名字以及密码正确路由跳转至个人页面
                 this.$message.success("欢迎用户"+localStorage['username']+"登录！");
                 payload = localStorage['username']
                 this.$store.commit('updateMessage', payload);
            } else if (this.loginuser === "") {
                //名字为空
                // alert("用户名不为空");
                this.$message.error("用户名不为空");
            } else if (this.loginpassword === "") {
                //密码为空
                // alert("密码不为空");
                this.$message.error("密码不为空");
            } else {
                // alert("账号不存在，请注册后登录"); //查无此号
                this.$message.error("账号不存在，请注册后登录");
            }
        },
        finishregister: function () {
            if (localStorage["username"] === this.registeruser) {
                // alert("用户名已存在");
                //如果用户名已存在则无法注册
                this.$message.warning("用户名已经存在");
            } else if (this.registername === "") {
                // alert("用户名不能为空");
                this.$message.warning("用户名不能为空");
            } else {
                //将新用户信息存储到localStorage
                localStorage.setItem("username", this.registeruser);
                localStorage.setItem("password", this.registerpassword);
                // alert("注册成功");
                this.$message.success("注册成功");
            }
        },
    },
    mounted() {},
};
</script>

<style scoped>
.bgimg {
    width: 100%;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
    background: url(./imgs/bgc1.jpg) no-repeat center center;
    background-size: cover;
}
.form__title {
    font-weight: 300;
    margin: 0;
    margin-bottom: 1.25rem;
}

.link {
    color: #333;
    font-size: 0.9rem;
    margin: 1.5rem 0;
    text-decoration: none;
}

.container {
    /* background-color: ; */
    border-radius: 0.7rem;
    box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25),
        0 0.7rem 0.7rem rgba(0, 0, 0, 0.22);
    height: 420px;
    max-width: 758px;
    overflow: hidden;
    position: relative;
    width: 100%;
    margin-top: 200px;
    margin-left: 378px;
}

.container__form {
    height: 100%;
    position: absolute;
    top: 0;
    transition: all 0.6s ease-in-out;
}

.container--signin {
    left: 0;
    width: 50%;
    z-index: 2;
}

.container.right-panel-active .container--signin {
    transform: translateX(100%);
}

.container--signup {
    left: 0;
    opacity: 0;
    width: 50%;
    z-index: 1;
}

.container.right-panel-active .container--signup {
    animation: show 0.6s;
    opacity: 1;
    transform: translateX(100%);
    z-index: 5;
}

.container__overlay {
    height: 100%;
    left: 50%;
    overflow: hidden;
    position: absolute;
    top: 0;
    transition: transform 0.6s ease-in-out;
    width: 50%;
    z-index: 100;
}

.container.right-panel-active .container__overlay {
    transform: translateX(-100%);
}

.overlay {
    background-color:rgba(0, 0, 0, 0.1);
    /* background: url(./imgs/bgc1.jpg); */
    background-attachment: fixed;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    height: 100%;
    left: -100%;
    position: relative;
    transform: translateX(0);
    transition: transform 0.6s ease-in-out;
    width: 200%;
}

.container.right-panel-active .overlay {
    transform: translateX(50%);
}

.overlay__panel {
    align-items: center;
    display: flex;
    flex-direction: column;
    height: 100%;
    justify-content: center;
    position: absolute;
    text-align: center;
    top: 0;
    transform: translateX(0);
    transition: transform 0.6s ease-in-out;
    width: 50%;
}

.overlay--left {
    transform: translateX(-20%);
}

.container.right-panel-active .overlay--left {
    transform: translateX(0);
}

.overlay--right {
    right: 0;
    transform: translateX(0);
}

.container.right-panel-active .overlay--right {
    transform: translateX(20%);
}

.btn {
    position: initial !important;;
    width: 137px;
    height: 45px;
    background-color: #0367a6;
    background-image: linear-gradient(171deg, #b4cfe2 0%, #202627 74%);
    border-radius: 20px;
    /* border: 1px solid #0367a6; */
    color: #e9e9e9;
    cursor: pointer;
    font-size: 1.2rem;
    font-weight: bold;
    letter-spacing: 0.1rem;
    /* padding: 0.9rem 4rem; */
    text-transform: uppercase;
    transition: transform 80ms ease-in;
}

.form > .btn {
    margin-top: 1.5rem;
}

.btn:active {
    transform: scale(0.95);
}

.btn:focus {
    outline: none;
}

.form {
    background-color: #e9e9e9;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding: 0 3rem;
    height: 100%;
    text-align: center;
}

.input {
    background-color: #fff;
    border: none;
    padding: 0.9rem 0.9rem;
    margin: 0.5rem 0;
    width: 100%;
}

@keyframes show {
    0%,
    49.99% {
        opacity: 0;
        z-index: 1;
    }
    50%,
    100% {
        opacity: 1;
        z-index: 5;
    }
}
</style>

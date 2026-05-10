// function logininandup() {
// const signInBtn = document.getElementById("signIn");
// const signUpBtn = document.getElementById("signUp");
// const fistForm = document.getElementById("form1");
// const secondForm = document.getElementById("form2");
// const container = document.querySelector(".container");
// signInBtn.addEventListener("click", function() {
//     container.classList.remove("right-panel-active")
// });
// signUpBtn.addEventListener("click", function() {
//     container.classList.remove('right-panel-active')
// });
// fistForm.addEventListener("submit", function(e) {
//     e.defaultPrevented()
// });
// secondForm.addEventListener("submit", function(e) {
//     e.defaultPrevented()
// });
// fistForm.addEventListener("submit", (e) => e.preventDefault());
// secondForm.addEventListener("submit", (e) => e.preventDefault());
// }

// export {
//     logininandup
// }
// const signInBtn = document.getElementById("signIn");
// const signUpBtn = document.getElementById("signUp");
// const fistForm = document.getElementById("form1");
// const secondForm = document.getElementById("form2");
// const container = document.querySelector(".container");
// console.log(signInbtn);
// signInBtn.addEventListener("click", function() {
//     container.classList.remove("right-panel-active");
// });

// signUpBtn.addEventListener("click", function() {
//     container.classList.add("right-panel-active");
// });

// fistForm.addEventListener("submit", function(e) {
//     e.preventDefault();
// });
// secondForm.addEventListener("submit", function(e) {
//     e.preventDefault();
// });
function local() {
    const signInBtn = document.getElementById("signIn");
    const signUpBtn = document.getElementById("signUp");
    const fistForm = document.getElementById("form1");
    const secondForm = document.getElementById("form2");
    const container = document.querySelector(".container");
    signInBtn.addEventListener("click", function() {
        container.classList.remove("right-panel-active")
    });
    signUpBtn.addEventListener("click", function() {
        container.classList.remove('right-panel-active')
    });
    fistForm.addEventListener("submit", (e) => e.preventDefault());
    secondForm.addEventListener("submit", (e) => e.preventDefault());
}
export default {
    local
}
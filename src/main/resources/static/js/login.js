const signUpBtn = document.getElementById("signUp");
const signInBtn = document.getElementById("signIn");
const container = document.querySelector(".container");

// 1. 비밀번호 입력창 정보 가져오기
let elInputPassword = document.querySelector('#password'); // input#password
// 2. 비밀번호 확인 입력창 정보 가져오기
let elInputPasswordRetype = document.querySelector('#password-retype'); // input#password-retype
// 3. 실패 메시지 정보 가져오기 (비밀번호 불일치)
let elMismatchMessage = document.querySelector('.mismatch-message'); // div.mismatch-message.hide
// 4. 실패 메시지 정보 가져오기 (8글자 이상, 영문, 숫자, 특수문자 미사용)
let elStrongPasswordMessage = document.querySelector('.strongPassword-message'); // div.strongPassword-message.hide
function togglePasswordVisibility() {

    const passwordInputs = document.querySelectorAll('input[name="pwd"]');
    const secondPasswordInput = passwordInputs[1]; // 두 번째 요소 선택
    const togglePasswordBtn = document.getElementById('togglePasswordBtn');

    if (secondPasswordInput.type === 'pwd') {
        secondPasswordInput.type = 'text';
        togglePasswordBtn.innerHTML = '&#x1F440;'; // 눈 모양 아이콘
    } else {
        secondPasswordInput.type = 'pwd';
        togglePasswordBtn.innerHTML = '&#x1F441;'; // 눈을 가린 모양 아이콘
    }
}


signUpBtn.addEventListener("click", () => {
    container.classList.add("right-panel-active");
});

signInBtn.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
});

function strongPassword (str) {
    return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
}

function isMatch (pwd1, pwd2) {
    return pwd1 === pwd2;
}

elInputPassword.onkeyup = function () {

    // console.log(elInputPassword.value);
    // 값을 입력한 경우
    if (elInputPassword.value.length !== 0) {
        if(strongPassword(elInputPassword.value)) {
            elStrongPasswordMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
        }
        else {
            elStrongPasswordMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
        }
    }
        // 값을 입력하지 않은 경우 (지웠을 때)
    // 모든 메시지를 가린다.
    else {
        elStrongPasswordMessage.classList.add('hide');
    }
};

elInputPasswordRetype.onkeyup = function () {

    // console.log(elInputPasswordRetype.value);
    if (elInputPasswordRetype.value.length !== 0) {
        if(isMatch(elInputPassword.value, elInputPasswordRetype.value)) {
            elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
        }
        else {
            elMismatchMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
        }
    }
    else {
        elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
    }
};

function checkNickname() {
    const nn = document.getElementById('nick-name').value;
    fetch(`/checkNickname/${nn}`)
        .then(response => response.json())
        .then(data => {
            if (data === true) {
                alert('사용할 수 있는 닉네임입니다.');
            } else {
                alert('이미 존재하는 닉네임입니다.');
            }
        })
        .catch(error => console.error('Error:', error));
}

// 회원가입시 이메일 인증 관련 코드

document.getElementById("mail-btn-input").addEventListener("click", function() {
    const username = document.getElementById("mail-input").value;

    // 서버로 이메일 주소를 보내어 인증 코드를 요청합니다.
    fetch('/sendVerificationCode', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(username)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // 이메일 주소를 반환받습니다.
        })
        .then(data => {
            alert("인증번호 발송"); // 이메일 주소를 알림으로 표시합니다.
            // 인증번호 입력란을 나타냅니다.
            let sendConfirmNumber = data.match(/\d+/)[0];
            console.log("sendConfirmNumber:", sendConfirmNumber); // 디버깅 메시지 추가
            document.querySelector('.mail-number-code').classList.remove('hide');

            // mail-btn-number 클릭 이벤트 핸들러 내에서 sendConfirmNumber를 사용합니다.
            document.getElementById("mail-btn-number").addEventListener("click", function() {
                const number1 = document.getElementById("mail-number").value.trim();

                console.log("number1:", number1); // 디버깅 메시지 추가
                console.log("sendConfirmNumber:", sendConfirmNumber); // 디버깅 메시지 추가

                if (number1 === sendConfirmNumber) {
                    alert("인증되었습니다.");
                    document.querySelector('.mail-number-code').classList.add('hide');
                } else {
                    alert("번호가 다릅니다.");
                }
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert("인증번호 발송에 실패했습니다.");
        });
});


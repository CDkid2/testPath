window.kakaoAsyncInit = function() {
    Kakao.Channel.createChatButton({
        container: '#kakao-talk-channel-chat-button',
    });
};

(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = 'https://t1.kakaocdn.net/kakao_js_sdk/2.6.0/kakao.channel.min.js';
    js.integrity = 'sha384-ty4605mXDCz/lA+RRt4bHCRa1c9uIaIi0JrsmmWVxaNJZzu58jMhJK8wAMqDxrYv';
    js.crossOrigin = 'anonymous';
    fjs.parentNode.insertBefore(js, fjs);
})(document, 'script', 'kakao-js-sdk');

function 맨위로이동() {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
}

// 스크롤 위치를 확인하여 버튼을 표시/숨김 처리
window.onscroll = function () {
    var backToTopBtn = document.getElementById('backToTopBtn');
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        backToTopBtn.style.display = 'block';
    } else {
        backToTopBtn.style.display = 'none';
    }
};
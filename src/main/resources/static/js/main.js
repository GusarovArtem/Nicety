let eventFromBack = document.querySelector('.back');

    eventFromBack.onclick = function () {
        history.back();
    };

//    Show Description
let popup = document.getElementById('showDescriptionBtn'),
    popupToggle = document.getElementById('showDescriptionPopup'),
    popupClose = document.querySelector('.close');

popupToggle.onclick = function () {
    popup.style.display="block";
};

popupClose.onclick = function () {
    popup.style.display="none";
};

window.onclick = function (e) {
    if(e.target === popup) {
        popup.style.display="none";
    }
}

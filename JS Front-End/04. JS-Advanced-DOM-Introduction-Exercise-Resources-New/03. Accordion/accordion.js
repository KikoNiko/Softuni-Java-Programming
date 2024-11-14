function toggle() {
    const moreBtn = document.querySelector('.button');
    const extraContent = document.querySelector('#extra');
    if (extraContent.style.display === 'none' || !extraContent.style.display) {
        extraContent.style.display = 'block';
        moreBtn.textContent = 'Less'
    } else {
        extraContent.style.display = 'none';
        moreBtn.textContent = 'More';
    }
}
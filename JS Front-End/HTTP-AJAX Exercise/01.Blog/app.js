function attachEvents() {
    const baseUrl = 'http://localhost:3030/jsonstore/blog';

    document.querySelector('#btnLoadPosts').addEventListener('click', loadPosts);
    document.querySelector('#btnViewPost').addEventListener('click', viewPost);

    const selectEl = document.querySelector('#posts');
    const postTitle = document.querySelector('#post-title');
    const postBody = document.querySelector('#post-body');
    const postComments = document.querySelector('#post-comments');

    function loadPosts(e) {
        selectEl.innerHTML = '';
        fetch(baseUrl + '/posts')
            .then(response => response.json())
            .then(posts => {

                Object.values(posts).forEach(post => {

                    const optionEl = document.createElement('option');

                    Object.assign(optionEl.dataset, post);

                    optionEl.textContent = post.title;

                    selectEl.append(optionEl);

                });

            })
            .catch(error => console.error(error));
    }

    function viewPost(e) {
        
        fetch(baseUrl + '/comments')
            .then(response => response.json())
            .then(comments => {

                const optionEl = document.querySelector('option:checked');

                postTitle.textContent = optionEl.dataset.title;
                postBody.textContent = optionEl.dataset.body;

                Object.values(comments).forEach(comment => {

                    if (comment.postId === optionEl.dataset.id) {
                        const commentEl = document.createElement('li');
                        commentEl.textContent = comment.text;
                        postComments.append(commentEl);
                    }
                });
            })
            .catch(error => console.error(error));
    }
}

attachEvents();
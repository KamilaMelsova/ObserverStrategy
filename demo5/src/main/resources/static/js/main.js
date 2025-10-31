let allArticles = [];
async function loadArticles() {
    const res = await fetch('/api/articles');
    allArticles = await res.json();
    renderArticles(allArticles);
}
function renderArticles(articles) {
    const container = document.getElementById('articlesContainer');
    container.innerHTML = '';
    articles.forEach(a => {
        const card = document.createElement('div');
        card.className = 'article-card';
        card.innerHTML = `
          <div class="article-content">
            <h3>${a.title}</h3>
            <p>${a.content}</p>
            <small>Автор: ${a.author || 'Unknown'} | Category: ${a.category}</small>
          </div>
          ${a.imageUrl ? `<img src="${a.imageUrl}" alt="image">` : ''}
          <button class="subscribe-btn" data-author="${a.author}">Subscribe</button>
        `;
        container.appendChild(card);
    });
    addSubscribeEvents();
}
function addSubscribeEvents() {
    document.querySelectorAll('.subscribe-btn').forEach(btn => {
        btn.addEventListener('click', async () => {
            const author = btn.dataset.author;
            const strategy = document.getElementById('strategySelect').value;

            const res = await fetch('/api/subscribe', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({ name: 'User', author: author, strategy: strategy })
            });
            const msg = await res.text();
            showToast(`📨 ${msg}`);
        });
    });
}
document.getElementById('articleForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const article = {
        title: document.getElementById('title').value,
        content: document.getElementById('content').value,
        author: document.getElementById('author').value,
        category: document.getElementById('category').value,
        imageUrl: document.getElementById('imageUrl').value,
        featured: document.getElementById('featured').checked,
        breaking: document.getElementById('breaking').checked
    };
    await fetch('/api/articles', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(article)
    });
    showToast('Article is published!');
    e.target.reset();
    loadArticles();
});
function showToast(message) {
    const toast = document.createElement('div');
    toast.className = 'toast';
    toast.textContent = message;
    document.getElementById('toast-container').appendChild(toast);
    setTimeout(() => toast.remove(), 3000);
}
document.querySelectorAll('.nav-links li').forEach(li => {
    li.addEventListener('click', () => {
        const category = li.dataset.category;
        document.querySelectorAll('.nav-links li').forEach(nav => nav.classList.remove('active'));
        li.classList.add('active');
        if (category === "All") {
            renderArticles(allArticles);
        } else {
            const filtered = allArticles.filter(a => a.category === category);
            renderArticles(filtered);
        }
    });
});
loadArticles();


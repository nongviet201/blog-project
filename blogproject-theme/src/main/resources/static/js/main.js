    document.addEventListener("DOMContentLoaded", function() {
    var blogContent = document.querySelector('.blog-content');
    var maxLength = 200; // Số ký tự tối đa bạn muốn hiển thị

    // Lấy nội dung HTML từ phần tử
    var htmlContent = blogContent.innerHTML;

    // Sử dụng DOMParser để phân tích nội dung HTML thành cây DOM
    var parser = new DOMParser();
    var doc = parser.parseFromString(htmlContent, 'text/html');

    // Giới hạn độ dài của nội dung văn bản trong cây DOM
    if (doc.body.textContent.length > maxLength) {
    var trimmedText = doc.body.textContent.substring(0, maxLength);
    trimmedText = trimmedText.trim(); // Cắt ký tự trắng ở cuối
    blogContent.innerHTML = trimmedText + '...';
}
});


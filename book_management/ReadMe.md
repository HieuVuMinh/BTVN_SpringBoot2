# Chương trình đào tạo Spring Boot phiên bản nâng cấp

# Đầu mục API:

1. Sinh ra 1000 đầu sách sử dụng Java Faker. Ban đâu khi sinh ngẫu nhiên hãy tạo cả BookInventory với amount ngẫu nhiên từ 0 đến 100 quyển sách.
GET: http://localhost:8080/books/fake_data



2. Liệt kê các đầu sách sắp xếp theo tiêu chí title từ A-Z
GET: http://localhost:8080/books/sort


3. Tìm đầu sách có title chứa keyword nào đó
GET: http://localhost:8080/books/{title}


4. Liệt kê các đầu sách hiện đang hết amount = 0;
GET: http://localhost:8080/books/{title}


5. Giả lập lệnh mua sách buy_book, mặc định số lượng mua là 1 quyển, nhưng có thể mua nhiều hơn 1 quyển.
GET: http://localhost:8080/books/buy


6. Nếu số lượng mua lớn số lượng sách có trong kho hãy tạo một event để đặt hàng thêm sách nhập về kho.
GET: http://localhost:8080/books/import

# Khó Khăn

7. Hãy tạo một schedule routine cứ 1 phút chạy 1 lần để tìm ra các đầu sách chỉ còn 1 quyển trong kho để tiến hàng nhập hàng bổ xung, số lượng là 5 quyển mỗi lần bổ xung.

8. Dùng Spring Boot AOP để viết một advice ứng với annotation @Benchmark để đo đạc thời gian thực thi các lệnh REST

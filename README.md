![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk) ![Swing](https://img.shields.io/badge/UI-Java%20Swing-0275d8?style=flat-square) ![SQL Server](https://img.shields.io/badge/CSDL-SQL%20Server-CC2927?style=flat-square) ![Maven](https://img.shields.io/badge/Build-Maven-C71A36?style=flat-square)

# DUAN1 — Ứng dụng quản lý cửa hàng bán giày (Java Swing)

Ứng dụng desktop quản lý cửa hàng giày: đăng nhập & phân quyền, bán hàng tại quầy (giỏ hàng, khuyến mãi, thanh toán tiền mặt / chuyển khoản QR), quản lý sản phẩm – khách hàng – nhân viên – hóa đơn, xuất hóa đơn PDF và gửi qua email.

---

## 1. Tóm tắt nhanh

| Hạng mục | Nội dung |
|---|---|
| **Ngôn ngữ** | Java 17 (`maven.compiler.source/target` = 17) |
| **Giao diện** | Java Swing |
| **CSDL** | SQL Server — database `QuanLyGiay`, 12 bảng |
| **Kiến trúc** | 3 tầng: `ui` → `controller` → `dao` / `daoimpl` → `entity` (+ `service`, `util`) |
| **Quy mô** | 64 file `.java` · ~10.439 dòng code |
| **Mã dự án** | `groupId` `nhom4` · `artifactId` `DUAN1` · `version` `1.0` |
| **Vai trò** | Dự án cá nhân — **DEV** (toàn bộ chức năng) |

---

## 2. Chức năng chính

| Mô-đun (`ui/…`) | Tính năng |
|---|---|
| **Đăng nhập** — `frmDangNhap` | Đăng nhập bằng mã nhân viên + mật khẩu, hiện/ẩn mật khẩu. **Quên mật khẩu**: sinh OTP 6 số gửi email → nhập đúng OTP (tối đa 3 lần) → sinh mật khẩu ngẫu nhiên và gửi về email đăng ký. |
| **Trang chủ** — `frmTrangChu` | Menu điều hướng, hiển thị tên + ảnh nhân viên. **Phân quyền**: `idCV == 1` (Nhân viên) bị ẩn nút *Nhân viên* và *Thống kê*. |
| **Bán hàng** — `jpBanHang` | Tìm kiếm sản phẩm (ID / tên / màu / kích cỡ / loại / chất liệu / NCC), gợi ý khách hàng quen khi gõ SĐT, giỏ hàng thêm/xóa/sửa số lượng **cộng – trừ tồn kho trực tiếp**, chặn vượt tồn kho, chọn khuyến mãi, thanh toán, xuất PDF, gửi email. |
| **Thanh toán QR** — `jdGetQRThanhToan` | Hộp thoại hiển thị QR VietQR (`img.vietqr.io` — **cần Internet**). Đơn chỉ ghi nhận *Đã thanh toán* khi bấm **Hoàn thành**; bấm Hủy thì báo *"Đã hủy thanh toán"*. |
| **Sản phẩm** — `jpSanPham` | 2 tab: **Sản Phẩm** (CRUD, tìm theo ID/tên/màu/kích cỡ/loại/chất liệu/NCC, radio *Còn hàng / Hết hàng*, chọn ảnh `.jpg/.jpeg/.png`) và **Chi Tiết** (thêm/xóa Màu, Kích cỡ, **Loại giày**, Chất liệu; bảng Nhà cung cấp). |
| **Khách hàng** — `jpKhachHang` | Danh sách kèm **số lượng** và **số tiền đã mua**; sắp xếp *Mặc định / Số lượng đã mua / Số tiền đã mua*. Khách mới được tạo tự động trong luồng bán hàng. |
| **Nhân viên** — `jpNhanVien` | 2 tab: **Nhân Viên** (CRUD, nút *Tạo mã*, chọn ảnh, validate tên / SĐT / email / ngày sinh / tuổi 16–55) và **Chức Vụ** (CRUD chức vụ → phân quyền). |
| **Khuyến mãi** — `jpKhuyenMai` | CRUD: tên, % giảm (> 0), ngày bắt đầu/kết thúc, gắn với **Loại giày**. |
| **Hóa đơn** — `jpHoaDon` | Danh sách đầy đủ 12 cột (mã HD, khách, NV, ngày tạo/thanh toán, tổng tiền, tiền khách đưa, tiền trả lại, phiếu giảm giá, trạng thái, ghi chú); tìm theo **Mã HD**, lọc theo **ngày tạo / ngày thanh toán**; bấm dòng để xem chi tiết hóa đơn (SP, số lượng, đơn giá, giảm giá, thành tiền). |
| **Thống kê** — `jpThongKe` | Tab **Sản phẩm**: tổng số SP, số SP đã bán, bảng *ID – Tên SP – Giá bán – Số lượng bán – Doanh thu*; lọc theo khoảng **Từ – Đến** (JDateChooser) kết hợp lựa chọn *Lọc theo ngày / tháng / năm*. (Tab *Doanh thu* mới tạo khung, chưa triển khai — xem mục 8.) |
| **Đổi mật khẩu** — `jpDoiMatKhau` | Kiểm tra thời gian thực: **> 6 ký tự**, **ký tự đầu là chữ hoa**, có **số**, có **ký tự đặc biệt**, **không chứa dấu cách**; nhập lại phải khớp. |

### 2.1. Điểm nhấn luồng bán hàng

1. **Tạo đơn** sinh mã `HD…` bằng `SecureRandom` (6 ký tự ngẫu nhiên), lấy/tạo khách theo SĐT.
2. **Giỏ hàng** trừ tồn kho ngay khi thêm và hoàn lại tồn kho khi bớt/xóa; chặn vượt tồn.
3. **Thanh toán**: tiền mặt (tính tiền thừa, có check đủ tiền) hoặc chuyển khoản (QR VietQR, chỉ ghi *Đã thanh toán* khi bấm **Hoàn thành**); xuất PDF rồi tùy chọn gửi email.

---

## 3. Công nghệ sử dụng

| Công nghệ | Phiên bản | Vai trò |
|---|---|---|
| Java | 17 | Ngôn ngữ chính |
| Maven | — | Build & dependency; `maven-assembly-plugin` 3.1.0 → `jar-with-dependencies`, `Main-Class: ui.frmDangNhap` |
| Java Swing | JDK | Toàn bộ giao diện: `JFrame`, `JDialog`, `JPanel`, `JTable`, `FileDialog` (AWT — chọn ảnh & thư mục lưu PDF) |
| SQL Server | — | CSDL `QuanLyGiay` (12 bảng), script `daataaa.sql` |
| `mssql-jdbc` | 12.8.1.jre8 | Driver kết nối SQL Server |
| OpenPDF (`com.github.librepdf:openpdf`) | 1.3.30 | Xuất hóa đơn PDF A4 (`com.lowagie.text`), nhúng `/font/unicode.ttf` |
| JCalendar (`com.toedter:jcalendar`) | 1.4 | `JDateChooser`: ngày sinh, ngày khuyến mãi, lọc hóa đơn, lọc thống kê |
| `javax.mail` (`com.sun.mail:javax.mail`) | 1.6.2 | Gửi email `smtp.gmail.com:587` + STARTTLS, UTF-8 (OTP, mật khẩu mới, hóa đơn đính kèm) |
| NetBeans AbsoluteLayout | RELEASE230 | Bố cục tuyệt đối — dùng cho bố cục form *Bán hàng*; các form còn lại dùng `GroupLayout` |
| NetBeans IDE | — | Thiết kế giao diện (11 file `.form`), `nbactions.xml`, `nb-configuration.xml` |
| Lombok | 1.18.34 (`provided`) | **Chỉ khai báo trong `pom.xml`, thực tế KHÔNG dùng** — trong `util/TimeRange.java` các annotation chỉ còn dạng comment |

> **Không có** trong dự án: JFreeChart / biểu đồ, JavaFX, REST API, JUnit / test, CI/CD, Docker. Phần thống kê chỉ là bảng + nhãn số liệu tổng quan.

---

## 4. Kiến trúc và quyết định thiết kế

Kiến trúc 3 tầng, giao diện chỉ giao tiếp qua interface:

| Tầng | Package | Nội dung |
|---|---|---|
| **Presentation** | `ui` | 11 lớp giao diện (2 `JFrame`, 1 `JDialog`, 8 `JPanel`) kèm file `.form` thiết kế bằng NetBeans Form Editor |
| **Controller** | `controller` | `Controller_CRUD<Entity>` (interface generic ~20 thao tác: `create`, `update`, `delete`, `fillToTable`, `edit`, `clear`, `moveFirst/Previous/Next/Last`, `checkAll`…) + 5 interface chuyên biệt mở rộng nó — 5/8 panel implements (jpBanHang, jpSanPham, jpNhanVien, jpHoaDon, jpKhuyenMai) |
| **Data access** | `dao`, `daoimpl` | `Dao_CRUD<T, ID>` + 12 interface DAO và 12 lớp `*_Daoimpl` viết SQL thuần, thao tác qua **`PreparedStatement`** (không nối chuỗi SQL) |
| **Domain** | `entity` | 13 entity/DTO: 12 entity ứng với 12 bảng + `ThongKe` (DTO cho báo cáo) |
| **Service** | `service` | `HoaDonPDF` (xuất PDF), `MailSender` (gửi email) |
| **Utility** | `util` | `XJdbc`, `XQuery`, `XDialog`, `XIcon`, `XStr`, `XDate`, `TimeRange` |

| Quyết định thiết kế | Điểm mạnh | Nơi thể hiện |
|---|---|---|
| Tách lớp UI ↔ Controller ↔ DAO | Tách bạch trách nhiệm, dễ bảo trì | `ui/*` → `controller/*` → `daoimpl/*` |
| Interface generic `Dao_CRUD<T,ID>` + `Controller_CRUD<Entity>` | Tái dùng logic CRUD, giảm code lặp, ép kiểu ở compile-time | `dao/Dao_CRUD.java`, `controller/Controller_CRUD.java` |
| **`PreparedStatement` cho mọi truy vấn** (`XJdbc.getStmt`) | Chống SQL Injection, câu lệnh tái sử dụng được | `util/XJdbc.java:130-136` |
| `XQuery.readBean` map `ResultSet` → entity bằng reflection (`setXxx` ↔ cột `xxx`, hỗ trợ `LocalDateTime` qua `Timestamp`) | Không phải viết mapping thủ công cho từng POJO | `util/XQuery.java:87-121` |
| Quản lý kết nối tập trung một chỗ | Kiểm soát vòng đời connection tập trung | `util/XJdbc.java:24-53` |
| Validate nghiệp vụ bằng regex tại tầng UI | Ràng buộc dữ liệu rõ ràng, thông báo lỗi cụ thể | `jpNhanVien.isCheckNV()`, `jpBanHang.isCheckKH()`, `jpDoiMatKhau` |
| Nghiệp vụ khuyến mãi gắn theo **Loại giày** (`KhuyenMai.idLoai`) | Một khuyến mãi áp được cho cả nhóm sản phẩm | `jpBanHang.java:899-915` |
| Xác thực 2 lớp cho đổi mật khẩu + quên mật khẩu qua OTP | Không cần mã cũ khi quên, vẫn có OTP giới hạn số lần thử | `jpDoiMatKhau`, `frmDangNhap.java:218-240` |
| Phân quyền theo `idCV` (ẩn/hiện menu) | Đơn giản, đủ dùng cho quy mô cửa hàng nhỏ | `frmTrangChu.java:42-45` |

---

## 5. Cấu trúc thư mục

```
DuAn1/
├── pom.xml                       # Maven: Java 17, 6 dependency, assembly jar-with-dependencies
├── daataaa.sql                   # Tạo DB QuanLyGiay + 12 bảng + dữ liệu mẫu
├── nbactions.xml                 # Chạy/debug dự án từ NetBeans
├── nb-configuration.xml
├── anhnv/                        # 6 ảnh mẫu
├── hoadon/                       # 2 file hóa đơn PDF mẫu
└── src/main/
    ├── java/
    │   ├── ui/                   # 11 class giao diện + 11 file .form
    │   │   ├── frmDangNhap · frmTrangChu · jdGetQRThanhToan
    │   │   └── jpBanHang · jpSanPham · jpNhanVien · jpKhachHang
    │   │       jpHoaDon · jpKhuyenMai · jpThongKe · jpDoiMatKhau
    │   ├── controller/           # Controller_CRUD<Entity> + 5 interface chuyên biệt
    │   ├── dao/                  # Dao_CRUD<T,ID> + 12 interface DAO
    │   ├── daoimpl/              # 12 lớp thực thi SQL
    │   ├── entity/               # 13 entity/DTO
    │   ├── service/              # HoaDonPDF.java, MailSender.java
    │   ├── util/                 # XJdbc, XQuery, XDialog, XIcon, XStr, XDate, TimeRange
    │   ├── icon/                 # 231 file PNG
    │   ├── image/                # 22 file ảnh
    │   └── font/                 # unicode.ttf
    └── resources/
        ├── icon/                 # 232 file PNG
        ├── image/                # 22 file ảnh
        └── font/                 # unicode.ttf (font nhúng cho PDF)
```

> **Lưu ý:** `icon/`, `image/`, `font/` đang tồn tại ở **cả hai** vị trí `src/main/java/` và `src/main/resources/`. Hai bản `image/` giống hệt nhau; hai bản `icon/` chỉ khác đúng một file (`xoaGH1.png` chỉ có ở `resources`). Ứng dụng thực tế nạp tài nguyên từ classpath (`/icon/…`), tức `src/main/resources/`.

---

## 6. Cài đặt và chạy

**Yêu cầu môi trường**

- JDK 17 trở lên
- Maven 3.6+
- SQL Server (Express/Developer) chạy cục bộ
- Internet (bắt buộc khi dùng QR VietQR và gửi email)
- NetBeans (khuyến nghị, do dự án dùng file `.form`)

### Bước 1 — Tạo cơ sở dữ liệu

Chạy file `daataaa.sql` (tạo database `QuanLyGiay`, 12 bảng, khóa ngoại và dữ liệu mẫu):

```bash
# Nếu SQL Server chạy instance mặc định:
sqlcmd -S localhost -U sa -P <mat-khau> -i daataaa.sql

# Nếu cài bản Express (named instance):
sqlcmd -S localhost\SQLEXPRESS -U sa -P <mat-khau> -i daataaa.sql
```

Khuyến nghị dùng SQL Server Management Studio: mở `daataaa.sql` rồi Execute — script đã chứa `CREATE DATABASE QuanLyGiay` và `USE QuanLyGiay`.

### Bước 2 — Kiểm tra cấu hình kết nối

Thông tin kết nối đang **hardcode** trong `src/main/java/util/XJdbc.java` (dòng 25-28), không có file `.properties`:

```java
// src/main/java/util/XJdbc.java (dòng 25-28) — thông tin này đang hardcode trong source
var driver   = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
var dburl    = "jdbc:sqlserver://localhost;database=QuanLyGiay;encrypt=true;trustServerCertificate=true;";
var username = "<tài khoản SQL Server của bạn>";
var password = "<mật khẩu SQL Server của bạn>";
```

Đổi `username` / `password` cho khớp SQL Server trên máy bạn (giá trị gốc trong repo là `sa` / `123` — mật khẩu mặc định của SQL Server, chỉ dùng cho môi trường local).

> Sau khi sửa phải build lại: `mvn clean package` — vì cấu hình được đóng gói vào file `.class` trong jar.

### Bước 3 — Tạo thư mục lưu hóa đơn (tuỳ chọn)

Ứng dụng mở hộp thoại lưu file với thư mục mặc định `resources/hoadon` — thư mục này **không có sẵn** trong repo. Có thể tạo trước:

```bash
mkdir -p resources/hoadon
```

Hoặc khi hộp thoại lưu file hiện ra, chọn thư mục khác là được.

### Bước 4 — Cấu hình gửi email

Trong `src/main/java/service/MailSender.java` (dòng 24–25) đang là **placeholder**:

```java
private static String fromEmail    = "Tài khoản Gmail";
private static String appPassword = "App Password lấy từ gmail …";
```

Thay bằng Gmail của bạn và **App Password** (yêu cầu bật xác thực 2 yếu tố). SMTP: `smtp.gmail.com:587`, STARTTLS, UTF-8.

> Bỏ qua bước này nếu chỉ cần demo các chức năng khác — tính năng OTP / gửi hóa đơn sẽ không hoạt động.

### Bước 5 — Build và chạy

```bash
mvn clean package
java -jar target/DUAN1-1.0-jar-with-dependencies.jar
```

Hoặc mở dự án trong **NetBeans** rồi Run (F6). Main class: `ui.frmDangNhap`.

### Bước 6 — Đăng nhập bằng tài khoản mẫu

| Mã NV | Mật khẩu | Họ tên trong DB | Chức vụ (`idCV`) |
|---|---|---|---|
| `NV01` | `123` | Admin | 1 — Nhân viên |
| `NV02` | `123` | Nhân viên A | 1 — Nhân viên |

Chức vụ trong `daataaa.sql`: `id 1` = *Nhân viên*, `id 2` = *Quản lý*.

> **Lưu ý khi demo:** cả hai tài khoản mẫu đều có `idCV = 1` → menu **Nhân viên** và **Thống kê** sẽ bị ẩn. Muốn demo đủ chức năng (kể cả quyền *Quản lý*) thì sửa `idCV` của một tài khoản thành `2`:
>
> ```sql
> UPDATE NhanVien SET idCV = 2 WHERE maNhanVien = 'NV01';
> ```

---

## 7. Ảnh giao diện

<!--
  KHU VỰC CHÈN ẢNH — TÁC GIẢ TỰ THÊM
  Gợi ý: chụp màn hình các form chính, lưu vào thư mục `images/` cùng cấp README,
  rồi bỏ comment thẻ ảnh bên dưới (hoặc chèn thêm) tại đây.
-->

<!-- ![Màn hình đăng nhập](images/dang-nhap.png) -->

<!-- ![Trang chủ — menu điều hướng](images/trang-chu.png) -->

<!-- ![Bán hàng — giỏ hàng và thanh toán](images/ban-hang.png) -->

<!-- ![Thanh toán chuyển khoản — QR VietQR](images/thanh-toan-qr.png) -->

<!-- ![Quản lý sản phẩm](images/san-pham.png) -->

<!-- ![Quản lý khách hàng](images/khach-hang.png) -->

<!-- ![Quản lý nhân viên](images/nhan-vien.png) -->

<!-- ![Hóa đơn và chi tiết hóa đơn](images/hoa-don.png) -->

<!-- ![Thống kê](images/thong-ke.png) -->

---

## 8. Điểm cần cải thiện

Các hạn chế dưới đây được rà soát trực tiếp từ mã nguồn:

| Nhóm | Nội dung + căn cứ |
|---|---|
| **Cấu hình nhạy cảm nằm trong source** | DB `sa`/mật khẩu hardcode `util/XJdbc.java:25-28`; SMTP `service/MailSender.java:24-25`; số tài khoản QR `jdGetQRThanhToan.java:29`. Nên đọc từ `.properties` hoặc biến môi trường. |
| **Mật khẩu lưu plain text** | Cột `NhanVien.matKhau` lưu chuỗi gốc, so sánh bằng `equalsIgnoreCase` (`frmDangNhap.java:278`). Nên băm bằng BCrypt/Argon2 kèm salt. |
| **Giao diện chưa triển khai đầy đủ** | 83 chỗ `throw new UnsupportedOperationException("Not supported yet.")` — nhiều phương thức của `Controller_CRUD` chưa cài đặt, ví dụ `jpBanHang.update()` dòng 1293-1294. Nên rà lại interface cho gọn hoặc hoàn thiện.<br>Tab *Doanh thu* của thống kê rỗng (`jpThongKe.java:301-314`).<br>Nút `btnTrangchu` chưa gắn sự kiện (`frmTrangChu.java`). |
| **Lỗi logic cần rà lại** | `jpBanHang.create()` gán `hoadon.setIdKhuyenMai(idkh)` (dòng 1285) trong khi `idkh` là **ID khách hàng** (trả về từ `getOrCreateCustomer`, dòng 1275) — đúng ra phải là ID khuyến mãi; luồng thanh toán ở dòng 922 thì lại gán đúng `km.getId()`. Ngoài ra `jpKhachHang` không validate định dạng SĐT/email (khác `jpBanHang` và `jpNhanVien`). |
| **Đường dẫn & tài nguyên** | `FileDialog` chọn ảnh hardcode `F:/FPoly/PRO1041/DuAn1/anhnv` (`jpNhanVien.java:663`); thư mục lưu PDF `resources/hoadon` không tồn tại trong repo (`jpBanHang.java:856,860`); `icon`/`image`/`font` bị nhân bản ở cả `src/main/java/` và `src/main/resources/`; `frmTrangChu.nhanVienHienTai` khởi tạo sẵn một object nhân viên hardcode (`frmTrangChu.java:28-36`, được gán đè khi đăng nhập tại `frmDangNhap.java:280`). |
| **Chưa có kiểm thử tự động** | Toàn bộ kiểm thử bằng tay trên giao diện — chưa có JUnit, chưa có CI. Nên bắt đầu bằng unit test cho tầng DAO. |
| **Code chưa dùng đến** | `util/TimeRange` (có `today()`/`thisWeek()`… nhưng không nơi nào gọi); Lombok khai báo trong `pom.xml` nhưng chỉ còn dạng comment trong `util/TimeRange.java:5-6`. |

---

## 9. Thông tin tác giả

| | |
|---|---|
| **Họ tên** | Nguyễn Ngọc Hiếu |
| **Vai trò** | DEV — thiết kế & lập trình toàn bộ chức năng (dự án cá nhân) |
| **Email** | `tkredao02@gmail.com` |
| **SĐT** | `` |
| **GitHub** | [github.com/hieujunior452](https://github.com/hieujunior452) |
| **LinkedIn** | `` |

---------------------------------------------------------
-- CREATE DATABASE
---------------------------------------------------------
CREATE DATABASE QuanLyGiay;
GO
USE QuanLyGiay;
GO


---------------------------------------------------------
-- TABLES
---------------------------------------------------------

-- 1. ChatLieu
CREATE TABLE ChatLieu (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenChatLieu NVARCHAR(100) NOT NULL UNIQUE
);


-- 2. ChucVu
CREATE TABLE ChucVu (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenChucVu NVARCHAR(100) NOT NULL UNIQUE
);


-- 3. KhachHang
CREATE TABLE KhachHang (
    id INT IDENTITY(1,1) PRIMARY KEY,
    hoVaTen NVARCHAR(100) NOT NULL,
    sDT VARCHAR(15) UNIQUE NOT NULL,
    email VARCHAR(100) NOT NULL
);


-- 4. KichCo
CREATE TABLE KichCo (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenKichCo NVARCHAR(100) NOT NULL UNIQUE
);


-- 5. Mau
CREATE TABLE Mau (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenMau NVARCHAR(100) NOT NULL UNIQUE
);


-- 6. LoaiGiay
CREATE TABLE LoaiGiay (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenLoai NVARCHAR(100) NOT NULL UNIQUE
);


-- 7. NhaCungCap
CREATE TABLE NhaCungCap (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenNCC NVARCHAR(100) NOT NULL UNIQUE,
    diaChi NVARCHAR(255),
    sDT VARCHAR(15),
    email VARCHAR(100),
    trangThai BIT DEFAULT 1
);


-- 8. ChucVu (for NhanVien)
-- (đã tạo bên trên)


-- 9. NhanVien
CREATE TABLE NhanVien (
    id INT IDENTITY(1,1) PRIMARY KEY,
    maNhanVien VARCHAR(20) NOT NULL UNIQUE,
    matKhau VARCHAR(100) NOT NULL,
    hoVaTen NVARCHAR(100) NOT NULL,
    ngaySinh DATE,
    gioiTinh BIT,
    diaChi NVARCHAR(255),
    sDT VARCHAR(15),
    email VARCHAR(100),
    idCV INT,
    hinh VARCHAR(Max),
    trangThai BIT,
    FOREIGN KEY (idCV) REFERENCES ChucVu(id)
);


-- 10. KhuyenMai
CREATE TABLE KhuyenMai (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenKM NVARCHAR(100),
    phanTramGiam INT DEFAULT 0,
    ngayBatDau DATE,
    ngayKetThuc DATE,
    trangThai BIT DEFAULT 1,
    idLoai INT,
    FOREIGN KEY (idLoai) REFERENCES LoaiGiay(id)
);


-- 11. SanPham
CREATE TABLE SanPham (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenGiay NVARCHAR(100),
    soLuong INT,
    giaNhap MONEY,
    idMau INT,
    size INT,
    idLoaiGiay INT,
    idChatLieu INT,
    hinh VARCHAR(MAX),
    donGia MONEY,
    trangThai BIT,
    moTa NVARCHAR(MAX),
    idNhaCungCap INT,
    FOREIGN KEY (idMau) REFERENCES Mau(id),
    FOREIGN KEY (size) REFERENCES KichCo(id),
    FOREIGN KEY (idLoaiGiay) REFERENCES LoaiGiay(id),
    FOREIGN KEY (idChatLieu) REFERENCES ChatLieu(id),
    FOREIGN KEY (idNhaCungCap) REFERENCES NhaCungCap(id)
);


-- 12. HoaDon
CREATE TABLE HoaDon (
    id INT IDENTITY(1,1) PRIMARY KEY,
    maHoaDon NVARCHAR(50) UNIQUE,
    ngayTao DATETIME,
    ngayThanhToan DATETIME,
    maNhanVien VARCHAR(20),
    phuongThucThanhToan NVARCHAR(50),
    tongTien MONEY DEFAULT 0,
    tienKhachDua MONEY DEFAULT 0,
    tienTraLai MONEY DEFAULT 0,
    trangThai INT DEFAULT 0,
    ghiChu NVARCHAR(MAX),
    idKhachHang INT,
    idKhuyenMai INT,
    FOREIGN KEY (idKhachHang) REFERENCES KhachHang(id),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien),
    FOREIGN KEY (idKhuyenMai) REFERENCES KhuyenMai(id)
);


-- 13. HoaDonChiTiet
CREATE TABLE HoaDonChiTiet (
    id INT IDENTITY(1,1) PRIMARY KEY,
    idHoaDon INT,
    idSanPham INT,
    soLuong INT CHECK (soLuong > 0),
    donGia MONEY,
    giamGia MONEY DEFAULT 0,
    thanhTien MONEY,
    maHoaDon NVARCHAR(50),
	FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
    FOREIGN KEY (idHoaDon) REFERENCES HoaDon(id),
    FOREIGN KEY (idSanPham) REFERENCES SanPham(id)
);



---------------------------------------------------------
-- INSERT DATA
---------------------------------------------------------

-- ChatLieu
INSERT INTO ChatLieu (tenChatLieu) VALUES
(N'Cao su'),
(N'Da'),
(N'Da tổng hợp'),
(N'Lưới'),
(N'Vải');


-- ChucVu
INSERT INTO ChucVu (tenChucVu) VALUES
(N'Nhân viên'),
(N'Quản lý');


-- KhachHang
INSERT INTO KhachHang (hoVaTen, sDT, email) VALUES
(N'Nguyễn Ngọc Hiếu', '0975653220', 'hieudubaizz@gmail.com'),
(N'Nguyễn Ngọc Hiếu', '0329094765', 'hieudubaizz@gmail.com');


-- KichCo
INSERT INTO KichCo (tenKichCo) VALUES
(N'36'), (N'37'), (N'38'), (N'39'),
(N'40'), (N'41'), (N'42');


-- Mau
INSERT INTO Mau (tenMau) VALUES
(N'Đen'),
(N'Đỏ'),
(N'Tím');


-- LoaiGiay
INSERT INTO LoaiGiay (tenLoai) VALUES
(N'Giày chạy bộ'),
(N'Giày đá bóng'),
(N'Giày thể thao'),
(N'Sneaker'),
(N'Sneaker cao cấp');


-- NhaCungCap (tối thiểu 1 bản ghi)
INSERT INTO NhaCungCap (tenNCC, diaChi, sDT, email)
VALUES (N'Nhà cung cấp A', N'Hà Nội', '0900000000', 'nccA@gmail.com');


-- NhanVien
INSERT INTO NhanVien
(maNhanVien, matKhau, hoVaTen, ngaySinh, gioiTinh, diaChi, sDT, email, idCV, hinh, trangThai)
VALUES
('NV01','123','Admin','2000-01-01',1,N'Hà Nội','0900000000','admin@gmail.com',1,'hinh1.jpg',1),
('NV02','123','Nhân viên A','2000-01-01',1,N'Hà Nội','0900000001','nv2@gmail.com',1,'hinh2.jpg',1);


-- KhuyenMai
INSERT INTO KhuyenMai
(tenKM, phanTramGiam, ngayBatDau, ngayKetThuc, trangThai, idLoai)
VALUES (N'Giảm 10% giày chạy bộ', 10, '2024-12-02', '2026-12-02', 1, 1);


-- SanPham
INSERT INTO SanPham
(tenGiay, soLuong, giaNhap, idMau, size, idLoaiGiay, idChatLieu, hinh, donGia, trangThai, moTa, idNhaCungCap)
VALUES
(N'Giày mẫu A', 100, 300000, 1, 1, 1, 1, 'a.jpg', 800000, 1, N'Mô tả...', 1),
(N'Giày mẫu B', 100, 300000, 1, 2, 1, 1, 'b.jpg', 800000, 1, N'Mô tả...', 1),
(N'Giày mẫu C', 100, 300000, 1, 3, 1, 1, 'c.jpg', 800000, 1, N'Mô tả...', 1);


-- HoaDon
INSERT INTO HoaDon
(maHoaDon, ngayTao, ngayThanhToan, maNhanVien, phuongThucThanhToan,
 tongTien, tienKhachDua, tienTraLai, trangThai, ghiChu, idKhachHang)
VALUES
('HD001','2025-12-02T14:04:30','2025-12-02T14:22:28','NV02',N'Chuyển khoản',3840000,0,0,1,N'',1),
('HD002','2025-12-02T18:26:14','2025-12-02T18:26:39','NV02',N'Tiền mặt',3040000,5000000,1960000,1,N'',1),
('HD003','2025-12-02T18:27:19','2025-12-02T18:27:42','NV02',N'Chuyển khoản',2160000,0,0,1,N'tuyệt',1);


-- HoaDonChiTiet
INSERT INTO HoaDonChiTiet
(idHoaDon, idSanPham, soLuong, donGia, giamGia, thanhTien, maHoaDon)
VALUES
(1, 1, 2, 800000, 0, 1600000, 'HD001'),
(1, 2, 3, 800000, 0, 2400000, 'HD001'),
(2, 1, 2, 800000, 0, 1600000, 'HD002'),
(3, 3, 3, 800000, 0, 2400000, 'HD003');


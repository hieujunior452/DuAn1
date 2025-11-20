CREATE TABLE Mau(
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenMau NVARCHAR(100) NOT NULL UNIQUE
);
GO

CREATE TABLE KichCo(
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenKichCo NVARCHAR(100) NOT NULL UNIQUE
);
GO

CREATE TABLE LoaiGiay (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenLoai NVARCHAR(100) NOT NULL UNIQUE
);
GO

CREATE TABLE ChatLieu (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenChatLieu NVARCHAR(100) NOT NULL UNIQUE
);
GO

CREATE TABLE NhaCungCap (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenNCC NVARCHAR(100) NOT NULL UNIQUE,
    diaChi NVARCHAR(255) NULL,
    sDT VARCHAR(15) NULL,
    email VARCHAR(100) NULL,
    trangThai BIT NOT NULL DEFAULT 1
);
GO

CREATE TABLE ChucVu (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenChucVu NVARCHAR(100) NOT NULL UNIQUE
);
GO

CREATE TABLE NhanVien (
    id INT IDENTITY(1,1) PRIMARY KEY,
    maNhanVien VARCHAR(20) UNIQUE NOT NULL,
    matKhau VARCHAR(100) NOT NULL,
    hoVaTen NVARCHAR(100) NOT NULL,
    ngaySinh DATE NOT NULL,
    gioiTinh BIT NOT NULL,
    diaChi NVARCHAR(255) NOT NULL,
    sDT VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    idCV INT NOT NULL FOREIGN KEY REFERENCES ChucVu(id),
    hinh VARCHAR(255) NOT NULL,
    trangThai BIT NOT NULL
);
GO

CREATE TABLE Khachhang (
    id INT IDENTITY(1,1) PRIMARY KEY,
    hoVaTen NVARCHAR(100) NOT NULL,
    gioiTinh BIT NOT NULL,
    diaChi NVARCHAR(255) NOT NULL,
    sDT VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    soLuong INT,
    tongTien MONEY
);
GO

CREATE TABLE HoaDon (
    id INT IDENTITY(1,1) PRIMARY KEY,
    maHoaDon NVARCHAR(50) UNIQUE NOT NULL,
    ngayTao DATE NOT NULL DEFAULT GETDATE(),
    idKhachHang INT NOT NULL FOREIGN KEY REFERENCES Khachhang(id),
    maNhanVien VARCHAR(20) NOT NULL FOREIGN KEY REFERENCES NhanVien(maNhanVien),
    tongTien MONEY NOT NULL DEFAULT 0,
    giamGia MONEY NOT NULL DEFAULT 0,
    phuongThucThanhToan NVARCHAR(50) NOT NULL,
    trangThai BIT NOT NULL DEFAULT 1,
    ghiChu NVARCHAR(MAX) NULL
);
GO

CREATE TABLE HoaDonChiTiet (
    id INT IDENTITY(1,1) PRIMARY KEY,
    idHoaDon INT NOT NULL FOREIGN KEY REFERENCES HoaDon(id),
    idSanPham INT NOT NULL FOREIGN KEY REFERENCES SanPham(id),
    soLuong INT NOT NULL CHECK (soLuong > 0),
    donGia MONEY NOT NULL,
    thanhTien MONEY NOT NULL DEFAULT 0,
    ghiChu NVARCHAR(MAX) NULL
);
GO

CREATE TABLE SanPham (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tenGiay NVARCHAR(100) NOT NULL,
    soLuong INT NOT NULL,
    giaNhap MONEY NOT NULL,
    idMau INT NOT NULL FOREIGN KEY REFERENCES Mau(id),
    size INT NOT NULL FOREIGN KEY REFERENCES KichCo(id),
    idLoaiGiay INT NOT NULL FOREIGN KEY REFERENCES LoaiGiay(id),
    idChatLieu INT NOT NULL FOREIGN KEY REFERENCES ChatLieu(id),
    hinh VARCHAR(255) NOT NULL,
    donGia MONEY NOT NULL,
    trangThai BIT NOT NULL,
    moTa NVARCHAR(MAX) NOT NULL,
    idNhaCungCap INT NULL FOREIGN KEY REFERENCES NhaCungCap(id)
);
GO

CREATE TABLE KhuyenMai (
    id INT IDENTITY(1,1) PRIMARY KEY,
    maKM NVARCHAR(50) UNIQUE NOT NULL,
    tenKM NVARCHAR(100) NOT NULL,
    phanTramGiam INT NOT NULL DEFAULT 0,
    ngayBatDau DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    trangThai BIT NOT NULL DEFAULT 1
);
GO
GO
INSERT INTO Mau (tenMau)
VALUES 
    (N'Đỏ'),
    (N'Xanh'),
    (N'Đen'),
    (N'Trắng'),
    (N'Vàng');
	GO
INSERT INTO KichCo (tenKichCo)
VALUES 
    (N'38'),
    (N'39'),
    (N'40'),
    (N'41'),
    (N'42');
	GO
INSERT INTO LoaiGiay (tenLoai)
VALUES 
    (N'Thể thao'),
    (N'Thời trang'),
    (N'Dép'),
    (N'Boots');
	GO
INSERT INTO ChatLieu (tenChatLieu)
VALUES 
    (N'Da thật'),
    (N'Vải'),
    (N'Cao su'),
    (N'Da tổng hợp');
	GO
INSERT INTO ChucVu (tenChucVu)
VALUES 
    (N'Quản lý'),
    (N'Nhân viên')
	GO
INSERT INTO NhaCungCap (tenNCC, diaChi, sDT, email, trangThai)
VALUES 
    (N'Nhà cung cấp Nike VN', N'123 Đường ABC, TP.HCM', '0123456789', 'nikevn@example.com', 1),
    (N'Nhà cung cấp Adidas VN', N'456 Đường XYZ, Hà Nội', '0987654321', 'adidasvn@example.com', 1);
	GO
INSERT INTO KhuyenMai (maKM, tenKM, phanTramGiam, ngayBatDau, ngayKetThuc, trangThai)
VALUES 
    (N'KM001', N'Giảm 10% giày thể thao', 10, '2025-11-01', '2025-12-31', 1),
    (N'KM002', N'Giảm 20% dịp Tết', 20, '2026-01-01', '2026-02-01', 1);

GO
INSERT INTO SanPham (tenGiay, soLuong, giaNhap, idMau, size, idLoaiGiay, idChatLieu, hinh, donGia, trangThai, moTa, idNhaCungCap)
VALUES 
    (N'Giày Nike Air Max', 50, 1500000, 1, 1, 1, 1, 'hinh1.jpg', 2000000, 1, N'Giày thể thao thoải mái', 1),
    (N'Giày Nike Running', 30, 1200000, 2, 2, 1, 2, 'hinh2.jpg', 1800000, 1, N'Giày chạy bộ nhẹ', 1),
    (N'Giày Nike Fashion', 40, 1000000, 3, 3, 2, 3, 'hinh3.jpg', 1500000, 1, N'Giày thời trang hàng ngày', 2),
    (N'Dép Nike Summer', 60, 500000, 4, 4, 3, 4, 'hinh4.jpg', 800000, 1, N'Dép mùa hè thoáng khí', 1);
	GO
INSERT INTO NhanVien (maNhanVien, matKhau, hoVaTen, ngaySinh, gioiTinh, diaChi, sDT, email, idCV, hinh, trangThai)
VALUES 
    (N'NV001', 'password123', N'Nguyễn Văn A', '1990-01-01', 1, N'123 TP.HCM', '0123456789', 'nva@example.com', 1, 'avatar1.jpg', 1),
    (N'NV002', 'password456', N'Trần Thị B', '1995-05-05', 0, N'456 Hà Nội', '0987654321', 'ttb@example.com', 2, 'avatar2.jpg', 1),
    (N'NV003', 'password789', N'Lê Văn C', '1985-10-10', 1, N'789 Đà Nẵng', '0112233445', 'lvc@example.com', 2, 'avatar3.jpg', 1);


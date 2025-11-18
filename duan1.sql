CREATE TABLE ChucVu (
    id INT IDENTITY(1,1) PRIMARY KEY,
    maChucVu VARCHAR(20) NOT NULL UNIQUE,
    tenChucVu NVARCHAR(100),
    ngayTao DATETIME,
    ngaySua DATETIME,
    trangThai bit
);
CREATE TABLE NhanVien (
    id INT IDENTITY(1,1) PRIMARY KEY,
    maNhanVien VARCHAR(20) NOT NULL UNIQUE,
    matKhau VARCHAR(100) NOT NULL,
    hoVaTen NVARCHAR(100) NOT NULL,
    ngaySinh DATE,
    gioiTinh BIT,                 -- 1 = Nam, 0 = Nữ
    diaChi NVARCHAR(255),
    sDT VARCHAR(15),
    email VARCHAR(100),
    idCV INT,                     -- Khóa ngoại đến bảng ChucVu
    hinh VARCHAR(255),
    ngayTao DATETIME,
    ngaySua DATETIME,
    trangThai BIT,                -- 1 = hoạt động, 0 = không
    CONSTRAINT FK_NhanVien_ChucVu FOREIGN KEY (idCV) REFERENCES ChucVu(id)
);

-- Thêm dữ liệu vào bảng ChucVu
INSERT INTO ChucVu (maChucVu, tenChucVu, ngayTao, trangThai)
VALUES 
('CV01', N'Quản lý', GETDATE(), 1),
('CV02', N'Nhân viên bán hàng', GETDATE(), 1),
('CV03', N'Thu ngân', GETDATE(), 1),
('CV04', N'Kế toán', GETDATE(), 1);

INSERT INTO NhanVien
(maNhanVien, matKhau, hoVaTen, ngaySinh, gioiTinh, diaChi, sDT, email, idCV, hinh, ngayTao, trangThai)
VALUES
('NV01', '123456', N'Nguyễn Văn A', '1990-05-10', 1, N'123 Lý Thường Kiệt, Hà Nội', '0905123456', 'vana@example.com', 1, 'a.jpg', GETDATE(), 1),

('NV02', '123456', N'Trần Thị B', '1995-07-20', 0, N'45 Nguyễn Huệ, TP.HCM', '0912345678', 'thib@example.com', 2, 'b.jpg', GETDATE(), 1),

('NV03', '123456', N'Lê Văn C', '1998-03-15', 1, N'12 Hai Bà Trưng, Đà Nẵng', '0933123123', 'vanc@example.com', 3, 'c.jpg', GETDATE(), 1),

('NV04', '123456', N'Phạm Thị D', '1992-11-02', 0, N'56 Pasteur, Cần Thơ', '0944556677', 'thid@example.com', 4, 'd.jpg', GETDATE(), 0);

select * from NhanVien
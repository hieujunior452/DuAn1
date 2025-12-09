/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Administrator
 */
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import daoimpl.SanPham_Daoimpl;
import entity.*;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HoaDonPDF {

    private static final DecimalFormat df = new DecimalFormat("#,### VNĐ");
    private static SanPham_Daoimpl repoSP = new SanPham_Daoimpl();

    private static String formatDateTime(LocalDateTime dt) {
        if (dt == null) {
            return "";
        }
        return dt.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
    }
    
    public static void exportPDF(HoaDon hd, List<HoaDonChiTiet> listHDCT, KhachHang kh, String path) {
        try {
            Document document = new Document(PageSize.A4, 20, 20, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            BaseFont unicodeFont = BaseFont.createFont(
                    "/font/unicode.ttf",
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );

            Font font = new Font(unicodeFont, 12);
            Font fontBold = new Font(unicodeFont, 12, Font.BOLD);
            Font fontTitle = new Font(unicodeFont, 20, Font.BOLD);
            Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("======================================"));
            document.add(new Paragraph("Mã hóa đơn: " + hd.getMaHoaDon(), font));
            document.add(new Paragraph("Ngày tạo: " + formatDateTime(hd.getNgayTao()), font));
            document.add(new Paragraph("Nhân viên: " + hd.getMaNhanVien(), font));
            document.add(new Paragraph("\nThông tin khách hàng", font));
            document.add(new Paragraph("Tên KH: " + kh.getHoVaTen(), font));
            document.add(new Paragraph("SĐT: " + kh.getsDT(), font));
            document.add(new Paragraph("Email: " + (kh.getEmail() == null ? "" : kh.getEmail()), font));
            document.add(new Paragraph("======================================"));
            BigDecimal tongTien = BigDecimal.ZERO;
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell(new PdfPCell(new Phrase("Sản phẩm", fontBold)));
            table.addCell(new PdfPCell(new Phrase("SL", fontBold)));
            table.addCell(new PdfPCell(new Phrase("Đơn giá", fontBold)));
            table.addCell(new PdfPCell(new Phrase("Thành tiền", fontBold)));
            for (HoaDonChiTiet hdct : listHDCT) {
                String tenSp = repoSP.findById(hdct.getIdSanPham()).getTenGiay();
                table.addCell(new PdfPCell(new Phrase(tenSp, font)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(hdct.getSoLuong()), font)));
                table.addCell(new PdfPCell(new Phrase(df.format(hdct.getDonGia()), font)));
                table.addCell(new PdfPCell(new Phrase(df.format(hdct.getThanhTien()), font)));
                tongTien = tongTien.add(hdct.getThanhTien());
            }
            document.add(table);
            document.add(new Paragraph("======================================"));
            document.add(new Paragraph("Tổng tiền: " + df.format(tongTien)));
            if (hd.getPhuongThucThanhToan().equalsIgnoreCase("Tiền mặt")) {
                document.add(new Paragraph("Tiền khách đưa: " + df.format(hd.getTienKhachDua())));
                document.add(new Paragraph("Tiền trả lại: " + df.format(hd.getTienTraLai())));
            }
            Paragraph footer = new Paragraph("\nCảm ơn quý khách!", fontTitle);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);
            document.close();
            System.out.println("Xuất PDF thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

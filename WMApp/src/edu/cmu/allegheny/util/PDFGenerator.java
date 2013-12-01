/**
 * 
 */
package edu.cmu.allegheny.util;

/**
 * Pdf builder by using library from iText.
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

//import com.lowagie.text.*;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.*;

/**
 * First iText example: Hello World.
 */
public class PDFGenerator {

	/** Path to the resulting PDF file. */
//	public String fileName = "/Users/Jie/Desktop/hello.pdf";
	public String fileName;
	
	/** Font for this form */
	public static final Font[] FONT = new Font[4];
    static {
    	
        FONT[0] = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
        FONT[1] = new Font(FontFamily.HELVETICA, 12);
        FONT[2] = new Font(FontFamily.HELVETICA, 10);
        FONT[3] = new Font(FontFamily.HELVETICA, 8);
    }

	/**
	 * Creates a PDF file: hello.pdf
	 * 
	 * @param args
	 *            no arguments needed
	 */
//	public static void main(String[] args) throws DocumentException,
//			IOException {
//		PDFGenerator g = new PDFGenerator();
//	}

	
	public PDFGenerator(String fileName) throws DocumentException, IOException{
		this.fileName =fileName;  
		createPdf(fileName);
	}
	
	/**
	 * Creates a PDF document.
	 * 
	 * @param filename
	 *            the path to the new PDF document
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void createPdf(String filename) throws DocumentException,
			IOException {
		// step 1
		Document document = new Document();
		document.setPageSize(PageSize.A4);
		document.setMargins(36, 36, 36, 36);
		// step 2
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		// step 3
		document.open();
		// step 4
		document.add(createCountyContactInfo());
		document.add(createStoreInfo());
		document.add(createRecordTable());
		document.add(createInstruction());
//		document.add(createSignature());
		document.add(createDateTime());
		document.add(createViolationCode());
		// step 5
		document.close();
	}

	private static Paragraph createStoreInfo(){
    	
    	StringBuilder s = new StringBuilder();
    	s.append("Station_______________________  ");
    	s.append("Address______________________________________");
    	s.append("\nCity/State_____________  Zip________   ");
    	s.append("Phone_____________  Store ID_________________");
    	s.append("\n ");
   
        return new Paragraph(s.toString(), FONT[1]);
    }
    
	private static PdfPTable createRecordTable() throws DocumentException {
    	// a table with three columns
        PdfPTable table = new PdfPTable(12);
        
        int[] widthRatio = {2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3};
        table.setWidths(widthRatio);
        table.setWidthPercentage(100);
        
        // Create Top table header
        PdfPCell cell = new PdfPCell(new Phrase("Description of Equipment", FONT[0]));
        cell.setColspan(8);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Action Taken", FONT[0]));
        cell.setColspan(3);
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setColspan(1);
        table.addCell(cell);

        // Create Table detailed header
        Phrase p = new Phrase("Make of Pump", FONT[2]);
        table.addCell(p);
        p = new Phrase("Serial Number", FONT[2]);
        table.addCell(p);
        p = new Phrase("Pupm #", FONT[3]);
        table.addCell(p);
        p = new Phrase("Brand of Gas", FONT[3]);
        table.addCell(p);
        p = new Phrase("Gallons Drawn", FONT[3]);
        table.addCell(p);
        
        p = new Phrase("Error +- Slow", FONT[3]);
        table.addCell(p);
        p = new Phrase("Error +- Fast", FONT[3]);
        table.addCell(p);
        p = new Phrase("Tol. Table", FONT[3]);
        table.addCell(p);
        p = new Phrase("Appr.", FONT[3]);
        table.addCell(p);
        p = new Phrase("Adj", FONT[3]);
        table.addCell(p);
        p = new Phrase("Rej", FONT[3]);
        table.addCell(p);
        p = new Phrase("Remarks", FONT[1]);
        table.addCell(p);
        
        // Create contents in table
        int column = 12;
        for (int i = 0; i < 5 * column; i++){
        	table.addCell(" ");
        }
        
        return table;
    }
    
    
    private static PdfPTable createCountyContactInfo(){
    	
    	PdfPTable table = new PdfPTable(1);
    	
    	table.setWidthPercentage(40);
    	table.setHorizontalAlignment(Element.ALIGN_LEFT);
    	
    	StringBuilder s = new StringBuilder();
    	s.append("County of Allegheny");
    	s.append("\nBureau of Weights & Measures");
    	s.append("\n312 County Office Building");
    	s.append("\n542 Forbes Ave");
    	s.append("\nPittsburgh, PA 15219");
    	s.append("\n(412)350-2576 / Fax (412)350-2578");
    	
    	Phrase p = new Phrase(s.toString(), FONT[3]);
    	table.addCell(p);
    	
    	return table;
    	
    }
    
    private static PdfPTable createInstruction() throws DocumentException{
    
    	PdfPTable table = new PdfPTable(2);
    	table.setWidths(new int[]{2,1});
    	table.setWidthPercentage(100);
    	
    	// Create Instruction line
    	StringBuilder s = new StringBuilder();
    	s.append("Instructions__________________________________________________________________");
    	s.append("______________________________________________________________________________");
    	
    	Phrase p = new Phrase(s.toString(), FONT[1]);
    	PdfPCell cell = new PdfPCell(p);
    	cell.setRowspan(2);
    	cell.setBorder(Rectangle.NO_BORDER);
    	table.addCell(cell);
    	
    	// Create text in box
    	s = new StringBuilder();
    	s.append("The product used for testing as indicated on this Inspection report was");
    	s.append(" returned to the storage tank From which it was drawn.");
		p = new Phrase(s.toString(), FONT[2]);
		cell = new PdfPCell(p);
		table.addCell(cell);
		table.setSpacingBefore(10);
    	
    	return table;
    }
 
    private PdfPTable createSignature() throws DocumentException, MalformedURLException, IOException {
		
    	PdfPTable table = new PdfPTable(4);
    	table.setWidths(new int[]{2,5,4,5});
    	table.setWidthPercentage(100);
    	
    	// Create text "signature inspector"
    	Phrase p = new Phrase("Signature\nInspector", FONT[1]);
    	PdfPCell cell = new PdfPCell(p);
    	cell.setRowspan(2);
    	cell.setBorder(Rectangle.NO_BORDER);
    	table.addCell(cell);
    	
    	// Post signature(a picture)
        Image img = Image.getInstance("/Users/Jie/Documents/workspace/PDFBuilder/signature.jpg");
        img.scaleToFit(150, 72);
        cell = new PdfPCell(img);
        cell.setRowspan(2);
    	cell.setBorder(Rectangle.NO_BORDER);
    	table.addCell(cell);
        table.setSpacingBefore(10);
        
        // Create text in box and signature line
        p = new Phrase("Signature Of Owner Or Operator Signature Acknowledges Receipt Of Form", FONT[3]);
    	cell = new PdfPCell(p);
    	cell.setRowspan(2);
    	table.addCell(cell);
       
    	p = new Phrase(" ");
    	cell = new PdfPCell(p);
    	cell.setBorder(Rectangle.NO_BORDER);
    	table.addCell(cell);
    	p = new Phrase("_______________________");
    	cell = new PdfPCell(p);
    	cell.setBorder(Rectangle.NO_BORDER);
    	table.addCell(cell);
    	
		return table;
	}
    

	private PdfPTable createDateTime() throws DocumentException {
		
		PdfPTable table = new PdfPTable(2);
    	table.setWidths(new int[]{7,3});
    	table.setWidthPercentage(100);
    	table.setSpacingBefore(12);
		
    	Phrase p = new Phrase("Date__________ Time_________", FONT[1]);
    	PdfPCell cell = new PdfPCell(p);
    	cell.setRowspan(2);
    	cell.setBorder(Rectangle.NO_BORDER);
    	cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    	table.addCell(cell);
    	
    	p = new Phrase("Approved:", FONT[1]);
    	cell = new PdfPCell(p);
    	table.addCell(cell);
    	p = new Phrase("Rejected:", FONT[1]);
    	cell = new PdfPCell(p);
    	table.addCell(cell);
    	
		return table;
	}

    private PdfPTable createViolationCode() throws DocumentException {
    	PdfPTable table = new PdfPTable(3);
    	table.setWidths(new int[]{1,1,1});
    	table.setWidthPercentage(100);
    	table.setSpacingBefore(12);

    	StringBuilder s = new StringBuilder();
    	s.append("Violation Codes:\n");
    	s.append("A. Product Not Identified\n");
    	s.append("B. No Unit Price Displayed\n");
    	s.append("C. No Security Seals\n");
    	s.append("D. Identification Plate\n");
    	s.append("E. Illegible Indicating Elements\n");
    	s.append("F. Not Type Approved\n");
    	s.append("G. Ground Tank Indentification");
    	Phrase p = new Phrase(s.toString(), FONT[2]);
    	table.addCell(p);
    	
    	s = new StringBuilder();
    	s.append(" \n");
    	s.append("H. Anti-Drain Valve Not Functioning\n");
    	s.append("J. Price Computation (Vo. X Unit Price)\n");
    	s.append("K. Dispenser Out of Tolerance\n");
    	s.append("L. Automatic Shut-Off\n");
    	s.append("M. Remote Display\n");
    	s.append("N. Faulty Zero Set-back Interlock\n");
    	s.append("O. Condition of Hose\n");
    	s.append("P. OTHER_______________");
    	p = new Phrase(s.toString(), FONT[2]);
    	table.addCell(p);
    	
    	s = new StringBuilder();
       	s.append("Product ID:\n");
    	s.append("1. Reg. Unleaded\n");
    	s.append("2. Prem. Unleaded\n");
    	s.append("3. Reg. Leaded\n");
    	s.append("4. Prem. Leaded\n");
    	s.append("5. Diesel\n");
    	s.append("6. Gasohol\n");
    	s.append("7. Kerosene\n");
    	s.append("8. OTHER_______________");
    	p = new Phrase(s.toString(), FONT[2]);
    	table.addCell(p);
    	
		return table;
	}

	
}
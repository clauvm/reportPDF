import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.util.Matrix;

import de.rototor.pdfbox.graphics2d.PdfBoxGraphics2D;


public class Main {


    public static void main(String[] args) {
    	try{
    	
			String fileName = "PdfWithGraphicsT1.pdf"; // name of our file
		    
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
				
			/*
			 * Creates the Graphics and sets a size in pixel. This size is used for the BBox of the XForm.
			 * So everything drawn outside (0x0)-(width,height) will be clipped.
			 */
			PdfBoxGraphics2D pdfBoxGraphics2D = new PdfBoxGraphics2D(document, 400, 400);
			
			/*
			 * Now do your drawing. By default all texts are rendered as vector shapes
			 */ 
			
			/* ... */
			
			/* 
			 * Dispose when finished
			 */
			pdfBoxGraphics2D.dispose();
			
			/*
			 * After dispose() of the graphics object we can get the XForm.
			 */
			PDFormXObject xform = pdfBoxGraphics2D.getXFormObject();
			
			/*
			 * Build a matrix to place the form
			 */
			Matrix matrix = new Matrix();
			/*
			 *  Note: As PDF coordinates start at the bottom left corner, we move up from there.
			 */
			matrix.translate(0, 20);
			
			contentStream.transform(matrix);
			
			/*
			 * Now finally draw the form. As we not do any scaling, the form drawn has a size of 5,5 x 5,5 inches, 
			 * because PDF uses 72 DPI for its lengths by default. If you want to scale, skew or rotate the form you can 
			 * of course do this. And you can also draw the form more then once. Think of the XForm as a stamper.
			 */
			contentStream.drawForm(xform);
			
			contentStream.close();
			
			document.save(fileName);
			document.close();
    	}
    	
    	catch(IOException e){
            
            System.out.println(e.getMessage());
            
            }
    	
	}
    
}
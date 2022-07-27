package br.com.sigoa.report;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import br.com.sigoa.dao.FuncionarioDAO;
import br.com.sigoa.dao.OsDAO;
import br.com.sigoa.model.OrdemDeServico;
import br.com.sigoa.model.Peca;
import br.com.sigoa.model.Servico;

public class Relatorio {
	
	public void gerarOS(OrdemDeServico os){
		
	    Document document = new Document(PageSize.A4, 50, 50, 50, 50);
	    
	    try {
	        // step 2:
	        // we create a writer that listens to the document 	
	        PdfWriter.getInstance(document, new FileOutputStream("OrdemDeServiço.pdf"));

	        // step 3: we open the document
	        document.open();
	        
	        Image imgTitulo = null;
	        try {
	            imgTitulo = Image.getInstance("Images\\OficinaImg4.png");
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "IMG Não encontrada", "Aviso", JOptionPane.WARNING_MESSAGE);
	        }
	        if (imgTitulo != null) {
	            imgTitulo.setAlignment(Element.ALIGN_CENTER);
	            document.add(imgTitulo);
	        }
	    
	        Paragraph paragrafoTitulo = new Paragraph();
	        paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
	        Chunk cNomeEmpresa = new Chunk("Oficina Mecânica Exemplo ME");
	        cNomeEmpresa.setFont(new Font(Font.TIMES_ROMAN, 12));
	        cNomeEmpresa.setBackground(Color.WHITE, 2, 2, 2, 2);
	        paragrafoTitulo.add(cNomeEmpresa);
	        document.add(paragrafoTitulo);
	        
	        Paragraph paragrafoTitulo2 = new Paragraph();
	        paragrafoTitulo2.setAlignment(Element.ALIGN_CENTER);
	        Chunk cCNPJ = new Chunk("028.092.405/0001-01");
	        cCNPJ.setFont(new Font(Font.TIMES_ROMAN, 12));
	        cCNPJ.setBackground(Color.WHITE, 2, 2, 2, 2);
	        paragrafoTitulo2.add(cCNPJ);
	        document.add(paragrafoTitulo2);
	        
	        Paragraph paragrafoTituloOS = new Paragraph();
	        paragrafoTituloOS.setAlignment(Element.ALIGN_CENTER);
	        Chunk cOS = new Chunk("Ordem de Serviço N° " + os.getId_ordem_servico());
	        cOS.setFont(new Font(Font.TIMES_ROMAN, 12));
	        cOS.setBackground(Color.LIGHT_GRAY, 2, 2, 2, 2);
	        paragrafoTituloOS.add(cOS);
	        document.add(paragrafoTituloOS);
	        
	        document.add(new Paragraph(" "));
	        
	        Paragraph paragrafoTituloData = new Paragraph();
	        
	        paragrafoTituloData.setAlignment(Element.ALIGN_LEFT);
	        Chunk cData = new Chunk("Status: " + os.getStatus_os());
	        cData.setFont(new Font(Font.TIMES_ROMAN, 12));
	        cData.setBackground(Color.WHITE, 2, 2, 2, 2);
	        paragrafoTituloData.add(cData);
	        document.add(paragrafoTituloData); 
	        
	        document.add(new Paragraph(" "));
	        
	        Paragraph paragrafoTituloFuncionario = new Paragraph();
	        paragrafoTituloFuncionario.setAlignment(Element.ALIGN_LEFT);
	        Chunk cFuncionario = new Chunk("Responsável: " + os.getNome_funcionairo());	        
	        cFuncionario.setFont(new Font(Font.TIMES_ROMAN, 12));
	        cFuncionario.setBackground(Color.WHITE, 2, 2, 2, 2);
	        paragrafoTituloFuncionario.add(cFuncionario);
	        document.add(paragrafoTituloFuncionario);
	        
	        document.add(new Paragraph(" "));
	       	        
	        Paragraph paragrafoTituloCliente = new Paragraph();
	        paragrafoTituloCliente.setAlignment(Element.ALIGN_LEFT);
	        Chunk cCliente = new Chunk("Cliente: " + os.getNome_cliente());
	        cCliente.setFont(new Font(Font.TIMES_ROMAN, 12));
	        cCliente.setBackground(Color.WHITE, 2, 2, 2, 2);
	        paragrafoTituloCliente.add(cCliente);
	        document.add(paragrafoTituloCliente);
	        
	        document.add(new Paragraph(" "));
	        
	        Paragraph paragrafoTituloPecas = new Paragraph();
	        paragrafoTituloPecas.setAlignment(Element.ALIGN_LEFT);
	        Chunk cTituloPecas = new Chunk("Peças:");
	        cTituloPecas.setFont(new Font(Font.TIMES_ROMAN, 14));
	        cTituloPecas.setBackground(Color.WHITE, 2, 2, 2, 2);
	        paragrafoTituloPecas.add(cTituloPecas);
	        document.add(paragrafoTituloPecas);
	        
	        PdfPTable tablePecas = new PdfPTable(4);
	        tablePecas.setWidthPercentage(98);
	        tablePecas.setWidths(new float[] { 2f, 1f, 1f, 1f });

	        PdfPCell celulaTitulo = new PdfPCell(new Phrase("ID"));
	        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
	        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
	        tablePecas.addCell(celulaTitulo);

	        celulaTitulo = new PdfPCell(new Phrase("PEÇA"));
	        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
	        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tablePecas.addCell(celulaTitulo);

	        celulaTitulo = new PdfPCell(new Phrase("QUANTIDADE"));
	        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
	        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
	        tablePecas.addCell(celulaTitulo);

	        celulaTitulo = new PdfPCell(new Phrase("VALOR TOTAL"));
	        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
	        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
	        tablePecas.addCell(celulaTitulo);
	        
	        PdfPTable tableProdutos = new PdfPTable(4);
	        tableProdutos.setWidthPercentage(98);
	        tableProdutos.setWidths(new float[] { 2f, 1f, 1f, 1f });

	        PdfPCell celulaTitulo2 = new PdfPCell(new Phrase("ID"));
	        celulaTitulo2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        celulaTitulo2.setBackgroundColor(Color.LIGHT_GRAY);
	        tableProdutos.addCell(celulaTitulo2);

	        celulaTitulo2 = new PdfPCell(new Phrase("PEÇA"));
	        celulaTitulo2.setBackgroundColor(Color.LIGHT_GRAY);
	        celulaTitulo2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tableProdutos.addCell(celulaTitulo2);

	        celulaTitulo2 = new PdfPCell(new Phrase("QUANTIDADE"));
	        celulaTitulo2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        celulaTitulo2.setBackgroundColor(Color.LIGHT_GRAY);
	        tableProdutos.addCell(celulaTitulo2);

	        celulaTitulo2 = new PdfPCell(new Phrase("VALOR TOTAL"));
	        celulaTitulo2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        celulaTitulo2.setBackgroundColor(Color.LIGHT_GRAY);
	        tableProdutos.addCell(celulaTitulo2);
	       	        
	        int contador = 1;
	        for (Peca peca : os.pecas_os) {

	            PdfPCell celulaNome = new PdfPCell(new Phrase(peca.getId_peca().toString()));
	            PdfPCell celulaQuantidade = new PdfPCell(new Phrase(peca.getNome_peca()));
	            celulaQuantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
	            PdfPCell celulaValor = new PdfPCell(new Phrase(peca.getQuantidade_peca_os().toString()));
	            celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
	            PdfPCell celulaTotalUnit = new PdfPCell(new Phrase("R$ " + String.valueOf(peca.getValor_peca_os())));
	            celulaTotalUnit.setHorizontalAlignment(Element.ALIGN_CENTER);

	            if (contador % 2 == 0) {
	                celulaNome.setBackgroundColor(Color.LIGHT_GRAY);
	                celulaQuantidade.setBackgroundColor(Color.LIGHT_GRAY);
	                celulaValor.setBackgroundColor(Color.LIGHT_GRAY);
	                celulaTotalUnit.setBackgroundColor(Color.LIGHT_GRAY);
	            }

	            tablePecas.addCell(celulaNome);
	            tablePecas.addCell(celulaQuantidade);
	            tablePecas.addCell(celulaValor);
	            tablePecas.addCell(celulaTotalUnit);

	            contador++;
	        }
	       	        
	        contador = 1;
	        for (Servico servico : os.servico_os) {

	            PdfPCell celulaNome = new PdfPCell(new Phrase(servico.getId_servico().toString()));
	            PdfPCell celulaQuantidade = new PdfPCell(new Phrase(servico.getNome_servico()));
	            celulaQuantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
	            PdfPCell celulaValor = new PdfPCell(new Phrase(servico.getQuantidade_servico_os().toString()));
	            celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
	            PdfPCell celulaTotalUnit = new PdfPCell(new Phrase("R$ " + String.valueOf(servico.getValor_servico_os())));
	            celulaTotalUnit.setHorizontalAlignment(Element.ALIGN_CENTER);
	            
	            if (contador % 2 == 0) {
	                celulaNome.setBackgroundColor(Color.LIGHT_GRAY);
	                celulaQuantidade.setBackgroundColor(Color.LIGHT_GRAY);
	                celulaValor.setBackgroundColor(Color.LIGHT_GRAY);
	                celulaTotalUnit.setBackgroundColor(Color.LIGHT_GRAY);
	            }

	            tableProdutos.addCell(celulaNome);
	            tableProdutos.addCell(celulaQuantidade);
	            tableProdutos.addCell(celulaValor);
	            tableProdutos.addCell(celulaTotalUnit);

	            contador++;
	        }
	        
	        document.add(new Paragraph(" "));
	        document.add(tablePecas);
	        
	        document.add(new Paragraph(" "));
	        
	        Paragraph paragrafoTituloServicos = new Paragraph();
	        paragrafoTituloServicos.setAlignment(Element.ALIGN_LEFT);
	        Chunk cTituloServicos = new Chunk("Serviços:");
	        cTituloServicos.setFont(new Font(Font.TIMES_ROMAN, 14));
	        cTituloServicos.setBackground(Color.WHITE, 2, 2, 2, 2);
	        paragrafoTituloServicos.add(cTituloServicos);
	        document.add(paragrafoTituloServicos);
	        document.add(new Paragraph(" "));
	        document.add(tableProdutos);
	        
	        document.add(new Paragraph(" "));
	        
	        Paragraph pTotalPecas = new Paragraph();
	        pTotalPecas.setAlignment(Element.ALIGN_RIGHT);
	        pTotalPecas.add(new Chunk("Total de Peças: R$ " + os.calcularValorTotalPecas(),
	                new Font(Font.TIMES_ROMAN, 14)));
	        document.add(pTotalPecas);
	        
	        Paragraph pTotalServicos = new Paragraph();
	        pTotalServicos.setAlignment(Element.ALIGN_RIGHT);
	        pTotalServicos.add(new Chunk("Total da Serviços: R$ " + os.calcularValorTotalServico(),
	                new Font(Font.TIMES_ROMAN, 14)));
	        document.add(pTotalServicos);
	        
	        Paragraph pTotalOS = new Paragraph();
	        pTotalOS.setAlignment(Element.ALIGN_RIGHT);
	        Double total = os.calcularValorTotalPecas()+ os.calcularValorTotalServico();
	        pTotalOS.add(new Chunk("Total O.S: R$ " + total,
	                new Font(Font.TIMES_ROMAN, 14)));
	        document.add(pTotalOS);
	       	        
	    } catch (DocumentException de) {
	        System.err.println(de.getMessage());
	    } catch (IOException ioe) {
	        System.err.println(ioe.getMessage());
	    }
	    // step 5: we close the document
	    document.close();
		}
	
    public static void abrirArquivoPDF() {
    	
    	try {
    		File arquivoPDF = new File("OrdemDeServiço.pdf");
    		
    		if(arquivoPDF.exists()) {
    			if(Desktop.isDesktopSupported()) {
    				Desktop.getDesktop().open(arquivoPDF);
    			}
    			else {
    				JOptionPane.showMessageDialog(null, "Arquivo não suportado", "Erro!", 0);
    			}
    		}
    		else {
    			JOptionPane.showMessageDialog(null, "Arquivo não criado", "Erro!", 0);
    		}
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

}
package br.gov.rj.fazenda.email.corp.resource.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.rj.fazenda.email.corp.entity.DarjIpva;
import br.gov.rj.fazenda.email.corp.resource.DarjIpvaResourceAPI;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;


@RestController
public class DarjIpvaResource implements DarjIpvaResourceAPI {

	@Override
	@GetMapping("api/darj/ipva/pdf")
	public ResponseEntity<byte[]> getBoleto() {
		try {
				
			
			  DarjIpva darjIpva = new DarjIpva(); darjIpva.setCdBarras("");
			  
			  darjIpva.setNoRazao("TESTE RAZÃO SOCIAL - XPTO");
			  darjIpva.setDsEndereco("RUA XPTO, 12, CASA 12344");
			  darjIpva.setNoMunicipio("RIO DE JANEIRO"); darjIpva.setSgUf("RJ");
			  darjIpva.setNuCep("2000-000"); //darjIpva.setdsGrupo
			  
			  darjIpva.setDsNatureza(""); darjIpva.setDescricao("descricao - 13/05/2022 - SDKFLASDFJ KASJFKJASDF ASKJDFKAJDS FKJS ADKFJ ASF ASDFJKJAS FKASFJKSAD AF SKJFASK DF");
			  
			  darjIpva.setbBradescoChequeEspecialDesc("dddddddd");
			  
			  darjIpva.setDtVencimento(null); darjIpva.setCpfCnpj(null);
			  
			  darjIpva.setNuNossoNumero("9839482394832");
			  
			  darjIpva.setSqDocArrecadacao(null); darjIpva.setVlPrincipal(null);
			  darjIpva.setVlMora(null); darjIpva.setVlMultaMora(null);
			  darjIpva.setVlMulta(null); darjIpva.setVlTotal(null);
			  
			  darjIpva.setDsLinhaDigitavel("00190500954014481606906809350314337370000000100");
			  darjIpva.setVia("1");
			  darjIpva.setCdBarras("1234567890123456789012345678901234567890");
			  darjIpva.setQrCode("00020126580014BR.GOV.BCB.PIX0136e4b932b3-5b2a-4bce-8be8-eed25f6edf6652040000530398654041.005802BR5923Tirso Araujo de Andrade6009SAO PAULO61080540900062070503***63045BE0");
			  
			  List<DarjIpva> darjIpvaList = new ArrayList<DarjIpva>();
			  darjIpvaList.add(darjIpva);
			 	
			

			//dynamic parameters required for report
			Map<String, Object> empParams = new HashMap<String, Object>();
			empParams.put("CompanyName", "TechGeekNext");
		//	empParams.put("darjIpvaData", new JRBeanCollectionDataSource(darjIpvaList));
			
			
	
			// COMPILANDO UM RELATÓRIO
			
			//JasperPrint empReport =
			//		JasperFillManager.fillReport
			//	   (
			//				JasperCompileManager.compileReport(
			//				ResourceUtils.getFile("classpath:report/darj.jrxml")
			//						.getAbsolutePath()) // path of the jasper report
			//				, empParams // dynamic parameters
			//				//, new JREmptyDataSource()
			//				 ,new JRBeanCollectionDataSource(darjIpvaList)
			//		);
			
			
		
			// CARREGANDO UM RELATÓRIO JÁ COMPILADO
			File file =	ResourceUtils.getFile("classpath:report/darj.jasper");
			
			JasperReport jasReport = (JasperReport) JRLoader.loadObject(file);
					
			
			JasperPrint empReport2 =JasperFillManager.fillReport(jasReport, empParams // dynamic parameters
							//, new JREmptyDataSource()
							 ,new JRBeanCollectionDataSource(darjIpvaList)
					);
			
			
			
			HttpHeaders headers = new HttpHeaders();
			//set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "darj.pdf");
			//create the report in PDF format
			return new ResponseEntity<byte[]>
					(JasperExportManager.exportReportToPdf(empReport2), headers, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	


}

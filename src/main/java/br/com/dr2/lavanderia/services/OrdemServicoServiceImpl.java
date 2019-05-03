package br.com.dr2.lavanderia.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import br.com.dr2.lavanderia.exception.ObjetoNaoEncontradoException;
import br.com.dr2.lavanderia.models.OrdemServico;
import br.com.dr2.lavanderia.models.Servico;
import br.com.dr2.lavanderia.models.ServicoOS;
import br.com.dr2.lavanderia.repositorys.OrdemServicoRepository;
import br.com.dr2.lavanderia.repositorys.ServicoRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {

	@Autowired
	private OrdemServicoRepository osRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public OrdemServico inserir(OrdemServico ordemServico) {

		if (ordemServico == null) {
			throw new IllegalArgumentException("Objeto OrdemServico não pode ser nulo.");
		}

		validarServicoOS(ordemServico);
		ordemServico.setDataCadastro(Calendar.getInstance());
		return osRepository.save(ordemServico);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public OrdemServico atualizar(OrdemServico ordemServico) {

		if (ordemServico.getId() <= 0) {
			throw new IllegalArgumentException("Objeto OrdemServico não pode ser nulo.");
		}
		validarServicoOS(ordemServico);
		ordemServico.setDataAlteracao(Calendar.getInstance());
		return osRepository.save(ordemServico);
	}

	@Transactional(readOnly = true)
	@Override
	public OrdemServico buscarPorId(int id) throws ObjetoNaoEncontradoException {
		Optional<OrdemServico> os = osRepository.findById(id);
		return os.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto Ordem de Serviço não encontrado."));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deletarPorId(int id) {
	}

	@Transactional(readOnly = true)
	@Override
	public List<OrdemServico> buscarTodos() {
		return osRepository.findAll();
	}

	private void validarServicoOS(OrdemServico ordemServico) {
		Servico servico = null;
		for (ServicoOS os : ordemServico.getServicos()) {
			os.setOrdemServico(ordemServico);
			servico = servicoRepository.getOne(os.getServico().getId());
			os.setServico(servico);
		}
	}

	/* JASPERREPORTS */
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void gerarPDF(int idOS, HttpServletResponse response) {
		
		Map<String, Object> param = null;
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		File file = null;
		InputStream stream = null;
		
		
		try {
			if(idOS > 0 && response != null) {
				file = ResourceUtils.getFile("classpath:report/os/pdf_os.jasper");
				stream = new FileInputStream(file);
				param = new HashMap<>();
				param.put("id_os", idOS);
				jasperReport = (JasperReport) JRLoader.loadObject(stream);
				jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource.getConnection());
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "inline; filename=ordem_servico.pdf");
				OutputStream outputStream = response.getOutputStream();
				JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
				outputStream.close();
			}
		}catch (JRException | IOException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				param = null;
				jasperReport = null;
				jasperPrint = null;
				file = null;
				stream.close();
			} catch (IOException e) {}
		}
	}
	
}

/**
 * @version 07/03/2005 <BR>
 * @author Setpoint Inform�tica Ltda./Robson Sanchez <BR>
 * 
 * Projeto: Freedom <BR>
 * 
 * Pacote: org.freedom.modulos.std <BR>
 * Classe:
 * @(#)FEstacao.java <BR>
 * 
 * Este arquivo � parte do sistema Freedom-ERP, o Freedom-ERP � um software livre; voc� pode redistribui-lo e/ou <BR>
 * modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); <BR>
 * na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o. <BR>
 * Este programa � distribuido na esperan�a que possa ser  util, mas SEM NENHUMA GARANTIA; <BR>
 * sem uma garantia implicita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. <BR>
 * Veja a Licen�a P�blica Geral GNU para maiores detalhes. <BR>
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este programa, se n�o, <BR>
 * de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 * Coment�rios sobre a classe...
 */

package org.freedom.modulos.std;

import java.awt.event.ActionListener;
import org.freedom.infra.model.jdbc.DbConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import org.freedom.acao.PostEvent;
import org.freedom.acao.PostListener;
import org.freedom.componentes.GuardaCampo;
import org.freedom.componentes.JCheckBoxPad;
import org.freedom.componentes.JRadioGroup;
import org.freedom.componentes.JTextFieldFK;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.componentes.JPanelPad;
import org.freedom.funcoes.Funcoes;
import org.freedom.telas.Aplicativo;
import org.freedom.telas.FDetalhe;

public class FEstacao extends FDetalhe implements PostListener, ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanelPad pinCab = new JPanelPad();

	private JPanelPad pinDet = new JPanelPad();

	private ListaCampos lcImp = new ListaCampos( this, "IP" );

	private ListaCampos lcPapel = new ListaCampos( this, "PP" );

	private JTextFieldPad txtCodEst = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtDescEst = new JTextFieldPad( JTextFieldPad.TP_STRING, 50, 0 );
	
	private JTextFieldPad txtFonteTxt = new JTextFieldPad( JTextFieldPad.TP_STRING, 30, 0 );
	
	private JTextFieldPad txtTamFonteTxt = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 2, 0 );

	private JCheckBoxPad cbModoDemoEst = new JCheckBoxPad( "Demonstrativo?", "S", "N" );

	private JCheckBoxPad cbNfeEst = new JCheckBoxPad( "Emite NFE?", "S", "N" );
	
	private JTextFieldPad txtNroImp = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 5, 0 );

	private JTextFieldPad txtCodImp = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtCodPapel = new JTextFieldPad( JTextFieldPad.TP_STRING, 10, 0 );

	private JTextFieldPad txtPortaWin = new JTextFieldPad( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldPad txtPortaLin = new JTextFieldPad( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldFK txtDescImp = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldFK txtDescPapel = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );

	private JCheckBoxPad cbImpPad = new JCheckBoxPad( "Impressora padr�o?", "S", "N" );

	private JCheckBoxPad cbImpGrafica = new JCheckBoxPad( "Impress�o gr�fica?", "S", "N" );

	private Vector<String> vValTipoUsoImp = new Vector<String>();

	private Vector<String> vLabTipoUsoImp = new Vector<String>();

	private JRadioGroup<?, ?> rgTipoUsoImp = null;

	public FEstacao() {

		setTitulo( "Cadastro de esta��es de trabalho" );
		setAtribos( 50, 10, 550, 520 );
		pinCab = new JPanelPad( 530, 50 );
	}

	private void montaTela() {

		// adicTab("Esta��o", pinEst);

		lcImp.add( new GuardaCampo( txtCodImp, "CodImp", "C�d.imp.", ListaCampos.DB_PK, false ) );
		lcImp.add( new GuardaCampo( txtDescImp, "DescImp", "Descri��o da impressora", ListaCampos.DB_SI, false ) );
		lcImp.add( new GuardaCampo( txtCodPapel, "CodPapel", "C�d.papel", ListaCampos.DB_SI, false ) );
		lcImp.montaSql( false, "IMPRESSORA", "SG" );
		lcImp.setQueryCommit( false );
		lcImp.setReadOnly( true );
		txtCodImp.setTabelaExterna( lcImp );

		lcPapel.add( new GuardaCampo( txtCodPapel, "CodPapel", "C�d.papel.", ListaCampos.DB_PK, false ) );
		lcPapel.add( new GuardaCampo( txtDescPapel, "DescPapel", "Descri��o do papel", ListaCampos.DB_SI, false ) );
		lcPapel.montaSql( false, "PAPEL", "SG" );
		lcPapel.setQueryCommit( false );
		lcPapel.setReadOnly( true );
		txtCodPapel.setTabelaExterna( lcPapel );

		lcCampos.addPostListener( this );
		pinCab = new JPanelPad( 740, 130 );
		setListaCampos( lcCampos );
		setAltCab( 130 );
		setPainel( pinCab, pnCliCab );
		cbModoDemoEst.setVlrString( "N" );
		cbNfeEst.setVlrString( "N" );

		adicCampo( txtCodEst, 7, 20, 80, 20, "Codest", "N� esta��o", ListaCampos.DB_PK, true );
		adicCampo( txtDescEst, 90, 20, 257, 20, "Descest", "Descri��o da esta��o de trabalho", ListaCampos.DB_SI, true );
		adicDB( cbModoDemoEst, 350, 20, 170, 20, "ModoDemoEst", "Modo", true );
		adicCampo( txtFonteTxt , 7, 60, 270, 20, "FonteTxt", "Fonte para visualiza��o de relat�rios texto", ListaCampos.DB_SI, false );
		adicCampo( txtTamFonteTxt , 280, 60, 65, 20, "TamFonteTxt", "Tamanho", ListaCampos.DB_SI, false );
		adicDB( cbNfeEst, 350, 60, 150, 20, "NfeEst", "NFE", true );
		setListaCampos( true, "ESTACAO", "SG" ); 
		lcCampos.setQueryInsert( false );

		setAltDet( 230 );
		pinDet = new JPanelPad( 740, 230 );
		setPainel( pinDet, pnDet );
		setListaCampos( lcDet );
		setNavegador( navRod );
		
		vValTipoUsoImp.addElement( "NF" );
		vValTipoUsoImp.addElement( "NS" );
		vValTipoUsoImp.addElement( "PD" );
		vValTipoUsoImp.addElement( "RS" );
		vValTipoUsoImp.addElement( "RG" );
		vValTipoUsoImp.addElement( "ET" );
		vValTipoUsoImp.addElement( "TO" );
		vLabTipoUsoImp.addElement( "Nota fiscal" );
		vLabTipoUsoImp.addElement( "Nota fiscal - servi�o" );
		vLabTipoUsoImp.addElement( "Pedido" );
		vLabTipoUsoImp.addElement( "Relat�rio simples" );
		vLabTipoUsoImp.addElement( "Relat�rio gr�fico" );
		vLabTipoUsoImp.addElement( "Etiquetas" );
		vLabTipoUsoImp.addElement( "Todos" );
		
		rgTipoUsoImp = new JRadioGroup<String, String>( 3, 3, vLabTipoUsoImp, vValTipoUsoImp );
		adicCampo( txtNroImp, 7, 20, 80, 20, "NroImp", "N� imp.", ListaCampos.DB_PK, true );
		adicCampo( txtCodImp, 90, 20, 80, 20, "CodImp", "C�d.imp.", ListaCampos.DB_FK, txtDescImp, true );
		adicDescFK( txtDescImp, 173, 20, 300, 20, "DescImp", "Descri��o da impressora" );
		adicCampo( txtCodPapel, 7, 60, 80, 20, "CodPapel", "C�d.papel", ListaCampos.DB_FK, txtDescPapel, true );
		adicDescFK( txtDescPapel, 90, 60, 300, 20, "DescPapel", "Descri��o do papel" );
		adicCampo( txtPortaWin, 7, 100, 100, 20, "PortaWin", "Porta Windows", ListaCampos.DB_SI, true );
		adicCampo( txtPortaLin, 110, 100, 100, 20, "PortaLin", "Porta Linux", ListaCampos.DB_SI, true );
		adicDB( cbImpPad, 213, 100, 147, 20, "ImpPad", "Padr�o", true );
		adicDB( cbImpGrafica, 363, 100, 150, 20, "ImpGrafica", "Gr�fica", true );
		adicDB( rgTipoUsoImp, 7, 140, 500, 70, "TipoUsoImp", "Tipo uso", true );

		lcDet.addPostListener( this );
		setListaCampos( true, "ESTACAOIMP", "SG" );
		lcDet.setQueryInsert( false );
		montaTab();

		tab.setTamColuna( 50, 0 );
		tab.setTamColuna( 70, 1 );
		tab.setTamColuna( 230, 2 );
		tab.setTamColuna( 70, 3 );
		tab.setTamColuna( 230, 4 );

	}

	public void afterPost( PostEvent pevt ) {

		PreparedStatement ps = null;
		String sSQL = null;
		int iNroImp = 0;
		if ( ( pevt.getListaCampos() == lcDet ) && ( cbImpPad.getVlrString().equals( "S" ) ) ) {
			try {
				iNroImp = txtNroImp.getVlrInteger().intValue();
				sSQL = "EXECUTE PROCEDURE SGESTACAOIMPSP01(?,?,?,?,?)";
				ps = con.prepareStatement( sSQL );
				ps.setInt( 1, Aplicativo.iCodEmp );
				ps.setInt( 2, ListaCampos.getMasterFilial( "SGESTACAOIMP" ) );
				ps.setInt( 3, txtCodEst.getVlrInteger().intValue() );
				ps.setInt( 4, txtNroImp.getVlrInteger().intValue() );
				ps.setString( 5, cbImpPad.getVlrString() );
				ps.execute();
				ps.close();
				con.commit();
				lcDet.carregaItens();
				txtNroImp.setVlrInteger( new Integer( iNroImp ) );
				lcDet.carregaDados();
			} catch ( SQLException err ) {
				Funcoes.mensagemErro( this, "Erro ajustando impressora padr�o!\n" + err.getMessage(), true, con, err );
			} finally {
				ps = null;
				sSQL = null;
				iNroImp = 0;
			}
		}
	}

	public void setConexao( DbConnection cn ) {

		super.setConexao( cn );
		lcImp.setConexao( cn );
		lcPapel.setConexao( cn );
		montaTela();
	}
}

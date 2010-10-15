/**
 * @version 14/07/2003 <BR>
 * @author Setpoint Inform�tica Ltda./Fernando Oliveira da Silva <BR>
 * 
 *         Projeto: Freedom <BR>
 * 
 *         Pacote: org.freedom.modulos.std <BR>
 *         Classe:
 * @(#)DLFechaCompra.java <BR>
 * 
 *                        Este arquivo � parte do sistema Freedom-ERP, o Freedom-ERP � um software livre; voc� pode redistribui-lo e/ou <BR>
 *                        modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); <BR>
 *                        na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o. <BR>
 *                        Este programa � distribuido na esperan�a que possa ser util, mas SEM NENHUMA GARANTIA; <BR>
 *                        sem uma garantia implicita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. <BR>
 *                        Veja a Licen�a P�blica Geral GNU para maiores detalhes. <BR>
 *                        Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este programa, se n�o, <BR>
 *                        de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 *                        Coment�rios sobre a classe...
 */

package org.freedom.modulos.std.view.dialog.utility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.freedom.bmps.Icone;
import org.freedom.infra.model.jdbc.DbConnection;
import org.freedom.library.functions.Funcoes;
import org.freedom.library.persistence.GuardaCampo;
import org.freedom.library.persistence.ListaCampos;
import org.freedom.library.swing.component.JButtonPad;
import org.freedom.library.swing.component.JCheckBoxPad;
import org.freedom.library.swing.component.JLabelPad;
import org.freedom.library.swing.component.JPanelPad;
import org.freedom.library.swing.component.JRadioGroup;
import org.freedom.library.swing.component.JTabbedPanePad;
import org.freedom.library.swing.component.JTablePad;
import org.freedom.library.swing.component.JTextFieldFK;
import org.freedom.library.swing.component.JTextFieldPad;
import org.freedom.library.swing.dialog.FFDialogo;
import org.freedom.library.swing.frame.Aplicativo;
import org.freedom.library.swing.frame.FPassword;
import org.freedom.library.swing.util.SwingParams;

public class DLFechaCompra extends FFDialogo implements FocusListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JTabbedPanePad tpn = new JTabbedPanePad();

	private JPanelPad pinFecha = new JPanelPad( 420, 300 );

	private JPanelPad pnPagar = new JPanelPad( JPanelPad.TP_JPANEL, new BorderLayout() );

	private JTablePad tabPag = new JTablePad();

	private JTextFieldPad txtCodCompra = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtVlrDescItCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtPercDescCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 6, 2 );

	private JTextFieldPad txtVlrDescCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtVlrBaseICMSST = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtVlrICMSST = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtPercAdicCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 6, 2 );

	private JTextFieldPad txtVlrLiqCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtQtdFreteCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDec );

	private JTextFieldPad txtVlrAdicCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtVlrProdCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtVlrFreteCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtVlrICMSCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtVlrIPICompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtVlrBaseICMS = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtCodPlanoPag = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtCodPag = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );
	
	private JTextFieldPad txtCodTipoCob = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtCodBanco = new JTextFieldPad( JTextFieldPad.TP_STRING, 3, 0 );

	private JTextFieldPad txtNParcPag = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtVlrParcItPag = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtVlrParcPag = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private JTextFieldPad txtDtVencItPag = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );

	private JTextFieldPad txtStatusCompra = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );

	private JTextFieldFK txtDescPlanoPag = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );

	private JTextFieldFK txtDescTipoCob = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );

	private JTextFieldFK txtDescBanco = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );

	private JCheckBoxPad cbEmitePedido = new JCheckBoxPad( "Emite Pedido?", "S", "N" );

	private JCheckBoxPad cbFinalizar = new JCheckBoxPad( "Finalizar?", "S", "N" );

	private JCheckBoxPad cbEmiteNota = new JCheckBoxPad( "Emite Nota Fiscal?", "S", "N" );

	private JCheckBoxPad cbAdicFreteCusto = new JCheckBoxPad( "Soma Valor do frete ao custo dos produtos.", "S", "N" );

	private JCheckBoxPad cbAdicAdicCusto = new JCheckBoxPad( "Soma Valor adicional ao custo dos produtos.", "S", "N" );

	private JCheckBoxPad cbAdicIPIBase = new JCheckBoxPad( "Soma IPI � base de c�lculo do ICMS.", "S", "N" );

	private JCheckBoxPad cbAdicFreteBase = new JCheckBoxPad( "Soma Frete � base de c�lculo do ICMS.", "S", "N" );

	private JCheckBoxPad cbAdicAdicBase = new JCheckBoxPad( "Soma Vlr. Adicionais � base de c�lculo do ICMS.", "S", "N" );

	private JRadioGroup<?, ?> rgFreteVD = null;

	private ListaCampos lcCompra = new ListaCampos( this );

	private ListaCampos lcPlanoPag = new ListaCampos( this, "PG" );

	private ListaCampos lcTipoCob = new ListaCampos( this, "TC" );
	
	private ListaCampos lcPagar = new ListaCampos( this );

	private ListaCampos lcBanco = new ListaCampos( this, "BO" );

	private ListaCampos lcItPagar = new ListaCampos( this );

	private Vector<String> vVals = new Vector<String>();

	private Vector<String> vLabs = new Vector<String>();

	public JButtonPad btFechar = new JButtonPad( "Fechar Compra", Icone.novo( "btOk.gif" ) );

	private int iCodCompraFecha = 0;

	private boolean bPodeSair = false;

	BigDecimal volumes = null;

	private JPanelPad pinLbCusto = new JPanelPad();

	private JPanelPad pinCusto = new JPanelPad();

	private JPanelPad pinLbValores = new JPanelPad();

	private JPanelPad pinValores = new JPanelPad();

	private JPanelPad pinLbFrete = new JPanelPad();

	private JPanelPad pinFrete = new JPanelPad();

	private JPanelPad pinLbImp = new JPanelPad();

	private JPanelPad pinImp = new JPanelPad();

	private JPanelPad pinLbTrib = new JPanelPad();

	private JPanelPad pinTrib = new JPanelPad();

	private JPanelPad pinLbICMS = new JPanelPad();

	private JPanelPad pinICMS = new JPanelPad();
	
	private ListaCampos lcConta = new ListaCampos( this, "CT" );
	
	private JTextFieldPad txtNumConta = new JTextFieldPad( JTextFieldPad.TP_STRING, 10, 0 );

	private JTextFieldFK txtDescConta = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );
	
	private final ListaCampos lcPlan = new ListaCampos( this, "PN" );
	
	private final ListaCampos lcCC = new ListaCampos( this, "CC" );
	
	private final JTextFieldPad txtCodPlan = new JTextFieldPad( JTextFieldPad.TP_STRING, 13, 0 );

	private final JTextFieldFK txtDescPlan = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );
	
	private final JTextFieldPad txtCodCC = new JTextFieldPad( JTextFieldPad.TP_STRING, 19, 0 );

	private final JTextFieldPad txtAnoCC = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 4, 0 );

	private final JTextFieldFK txtSiglaCC = new JTextFieldFK( JTextFieldPad.TP_STRING, 10, 0 );

	private final JTextFieldFK txtDescCC = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );
	
	private Map<String, Integer> prefere = null;

	public DLFechaCompra( DbConnection cn, Integer iCodCompra, Component cOrig, BigDecimal volumes, boolean NFe ) {

		super( cOrig );
		setConexao( cn );
		
		prefere = getPrefere();
		
		txtAnoCC.setVlrInteger( prefere.get( "anocc" ) );

		if ( NFe ) {
			cbEmiteNota.setText( "Emite NFE?" );
		}

		iCodCompraFecha = iCodCompra.intValue();
		setTitulo( "Fechar Compra" );
		setAtribos( 560, 610 );

		this.volumes = volumes;

		lcItPagar.setMaster( lcPagar );
		lcPagar.adicDetalhe( lcItPagar );
		lcItPagar.setTabela( tabPag );

		c.add( tpn );

		tpn.add( "Fechamento", pinFecha );
		tpn.add( "Pagar", pnPagar );

		vVals.addElement( "C" );
		vVals.addElement( "F" );
		vLabs.addElement( "CIF" );
		vLabs.addElement( "FOB" );
		rgFreteVD = new JRadioGroup<String, String>( 1, 2, vLabs, vVals );

		txtCodPlanoPag.setNomeCampo( "CodPlanoPag" );
		lcPlanoPag.add( new GuardaCampo( txtCodPlanoPag, "CodPlanoPag", "C�d.p.pg.", ListaCampos.DB_PK, false ) );
		lcPlanoPag.add( new GuardaCampo( txtDescPlanoPag, "DescPlanoPag", "Descri��o do plano de pagamento", ListaCampos.DB_SI, false ) );
		txtCodPlanoPag.setTabelaExterna( lcPlanoPag, null );
		txtCodPlanoPag.setFK( true );
		txtDescPlanoPag.setListaCampos( lcPlanoPag );
		lcPlanoPag.montaSql( false, "PLANOPAG", "FN" );
		lcPlanoPag.setQueryCommit( false );
		lcPlanoPag.setReadOnly( true );
		lcPlanoPag.setConexao( cn );

		txtCodTipoCob.setNomeCampo( "CodTipoCob" );
		lcTipoCob.add( new GuardaCampo( txtCodTipoCob, "CodTipoCob", "C�d.t.cob.", ListaCampos.DB_PK, false ) );
		lcTipoCob.add( new GuardaCampo( txtDescTipoCob, "DescTipoCob", "Descri��o do tipo de cobran�a", ListaCampos.DB_SI, false ) );
		txtCodTipoCob.setTabelaExterna( lcTipoCob, null );
		txtCodTipoCob.setFK( true );
		txtDescTipoCob.setListaCampos( lcTipoCob );
		lcTipoCob.montaSql( false, "TIPOCOB", "FN" );
		lcTipoCob.setQueryCommit( false );
		lcTipoCob.setReadOnly( true );
		lcTipoCob.setConexao( cn );
		txtCodTipoCob.setTabelaExterna( lcTipoCob, null );

		txtCodBanco.setNomeCampo( "CodBanco" );
		lcBanco.add( new GuardaCampo( txtCodBanco, "CodBanco", "C�d.banco", ListaCampos.DB_PK, false ) );
		lcBanco.add( new GuardaCampo( txtDescBanco, "NomeBanco", "Nome do banco", ListaCampos.DB_SI, false ) );
		txtDescBanco.setListaCampos( lcBanco );
		lcBanco.montaSql( false, "BANCO", "FN" );
		lcBanco.setQueryCommit( false );
		lcBanco.setReadOnly( true );
		lcBanco.setConexao( cn );
		txtCodBanco.setTabelaExterna( lcBanco, null );
		txtCodBanco.setFK( true );
		
		lcConta.add( new GuardaCampo( txtNumConta, "NumConta", "Num.Conta", ListaCampos.DB_PK, txtDescConta, false ) );
		lcConta.add( new GuardaCampo( txtDescConta, "DescConta", "Descri��o da conta", ListaCampos.DB_SI, false ) );
		lcConta.montaSql( false, "CONTA", "FN" );
		lcConta.setQueryCommit( false );
		lcConta.setReadOnly( true );
		txtNumConta.setFK( true );
		txtNumConta.setNomeCampo( "numconta" );
		txtNumConta.setTabelaExterna( lcConta, null );	
		lcConta.setConexao( cn );
		
		
		/************************
		 * FNPLANEJAMENTO       *
		 ************************/
		lcPlan.add( new GuardaCampo( txtCodPlan, "CodPlan", "C�d.plan.", ListaCampos.DB_PK, false ) );
		lcPlan.add( new GuardaCampo( txtDescPlan, "DescPlan", "Descri��o do planejamento", ListaCampos.DB_SI, false ) );
		lcPlan.setWhereAdic( "TIPOPLAN = 'D' AND NIVELPLAN = 6" );
		lcPlan.montaSql( false, "PLANEJAMENTO", "FN" );
		lcPlan.setReadOnly( true );
		txtCodPlan.setTabelaExterna( lcPlan, null );
		txtCodPlan.setFK( true );
		txtCodPlan.setNomeCampo( "CodPlan" );
		lcPlan.setConexao( cn );
		
		/***************
		 * FNCC        *
		 ***************/
		lcCC.add( new GuardaCampo( txtCodCC, "CodCC", "C�d.c.c.", ListaCampos.DB_PK, false ) );
		lcCC.add( new GuardaCampo( txtSiglaCC, "SiglaCC", "Sigla", ListaCampos.DB_SI, false ) );
		lcCC.add( new GuardaCampo( txtDescCC, "DescCC", "Descri��o", ListaCampos.DB_SI, false ) );
		lcCC.add( new GuardaCampo( txtAnoCC, "AnoCC", "Ano-Base", ListaCampos.DB_PK, false ) );
		lcCC.setReadOnly( true );
		lcCC.setQueryCommit( false );
		lcCC.setWhereAdic( "NIVELCC=10 AND ANOCC=" + prefere.get( "anocc" ) );
		lcCC.montaSql( false, "CC", "FN" );
		txtCodCC.setTabelaExterna( lcCC, null );
		txtCodCC.setFK( true );
		txtCodCC.setNomeCampo( "CodCC" );
		txtAnoCC.setTabelaExterna( lcCC, null );
		txtAnoCC.setFK( true );
		txtAnoCC.setNomeCampo( "AnoCC" );
		lcCC.setConexao( cn );

		txtCodCompra.setNomeCampo( "CodCompra" );
		
		txtCodPlanoPag.setTabelaExterna( lcPlanoPag, null );
		
		lcCompra.add( new GuardaCampo( txtCodCompra, "CodCompra", "N.pedido", ListaCampos.DB_PK, false ) );
		lcCompra.add( new GuardaCampo( txtCodPlanoPag, "CodPlanoPag", "Cod.p.pg.", ListaCampos.DB_FK, txtDescPlanoPag, false ) );
		lcCompra.add( new GuardaCampo( txtVlrLiqCompra, "VlrLiqCompra", "V.compra", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrICMSCompra, "VlrICMSCompra", "V.ICMS", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrICMSST, "VlrICMSSTCompra", "V.ICMS ST", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrIPICompra, "VlrIPICompra", "V.IPI", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrDescItCompra, "VlrDescItCompra", "% Desc.it.", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrDescCompra, "VlrDescCompra", "% Desc.it.", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrAdicCompra, "VlrAdicCompra", "V.adic.", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrProdCompra, "VlrProdCompra", "V.prod.", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrFreteCompra, "VlrFreteCompra", "V.prod.", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtStatusCompra, "StatusCompra", "Status", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtCodTipoCob, "CodTipoCob", "Cod.t.cob.", ListaCampos.DB_FK, txtDescTipoCob, false ) );
		lcCompra.add( new GuardaCampo( txtCodBanco, "CodBanco", "CodBanco", ListaCampos.DB_FK, txtDescBanco, false ) );
		lcCompra.add( new GuardaCampo( rgFreteVD, "TipoFreteCompra", "Tipo do frete", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( cbAdicFreteCusto, "AdicFreteCompra", "frete na campra", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( cbAdicAdicCusto, "AdicAdicCompra", "Vlr Adicional na campra", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtQtdFreteCompra, "QtdFreteCompra", "Qtd. de volumes na compra", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrBaseICMS, "VlrBaseICMSCompra", "Vlr. Base do ICMS", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrBaseICMSST, "VlrBaseICMSSTCompra", "Vlr. Base do ICMS ST", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtNumConta, "NumConta", "Nro.Conta", ListaCampos.DB_FK, txtDescConta, false ) );
		lcCompra.add( new GuardaCampo( txtCodPlan, "CodPlan", "C�d.Plan.", ListaCampos.DB_FK, txtDescPlan, false ) ); 
		lcCompra.add( new GuardaCampo( txtAnoCC, "AnoCC", "Ano.C.C.", ListaCampos.DB_SI, txtDescCC, false ) );
		lcCompra.add( new GuardaCampo( txtCodCC, "CodCC", "C�d.C.C.", ListaCampos.DB_FK, txtDescCC, false ) );

		lcCompra.montaSql( false, "COMPRA", "CP" );
		lcCompra.setConexao( cn );
		txtVlrLiqCompra.setListaCampos( lcCompra );
		txtVlrICMSCompra.setListaCampos( lcCompra );
		txtVlrIPICompra.setListaCampos( lcCompra );
		txtVlrAdicCompra.setListaCampos( lcCompra );
		txtPercAdicCompra.setListaCampos( lcCompra );
		txtVlrDescCompra.setListaCampos( lcCompra );
		txtVlrFreteCompra.setListaCampos( lcCompra );
		txtQtdFreteCompra.setListaCampos( lcCompra );
		txtPercDescCompra.setListaCampos( lcCompra );
		txtStatusCompra.setListaCampos( lcCompra );
		txtCodPlanoPag.setListaCampos( lcCompra );
		txtCodTipoCob.setListaCampos( lcCompra );

		JPanelPad pinTopPag = new JPanelPad( 400, 60 );
		pinTopPag.setPreferredSize( new Dimension( 400, 60 ) );
		pnPagar.add( pinTopPag, BorderLayout.NORTH );
		JScrollPane spnTabPag = new JScrollPane( tabPag );
		pnPagar.add( spnTabPag, BorderLayout.CENTER );

		txtVlrParcPag.setAtivo( false );

		pinTopPag.adic( new JLabelPad( "Valor Tot." ), 7, 0, 130, 20 );
		pinTopPag.adic( txtVlrParcPag, 7, 20, 130, 20 );

		txtCodPag.setNomeCampo( "CodPag" );
		lcPagar.add( new GuardaCampo( txtCodPag, "CodPag", "C�d.pgto.", ListaCampos.DB_PK, false ) );
		lcPagar.add( new GuardaCampo( txtVlrParcPag, "VlrParcPag", "Valor tot.", ListaCampos.DB_SI, false ) );
		lcPagar.montaSql( false, "PAGAR", "FN" );
		lcPagar.setConexao( cn );
		txtCodPag.setListaCampos( lcPagar );
		txtVlrParcPag.setListaCampos( lcPagar );
		txtCodBanco.setListaCampos( lcPagar );

		txtNParcPag.setNomeCampo( "NParcPag" );
		lcItPagar.add( new GuardaCampo( txtNParcPag, "NParcPag", "N.parc.", ListaCampos.DB_PK, false ) );
		lcItPagar.add( new GuardaCampo( txtVlrParcItPag, "VlrParcItPag", "Valor tot.", ListaCampos.DB_SI, false ) );
		lcItPagar.add( new GuardaCampo( txtDtVencItPag, "DtVencItPag", "Valor tot.", ListaCampos.DB_SI, false ) );
		lcItPagar.montaSql( false, "ITPAGAR", "FN" );
		lcItPagar.setConexao( cn );
		txtNParcPag.setListaCampos( lcItPagar );
		txtVlrParcItPag.setListaCampos( lcItPagar );
		txtDtVencItPag.setListaCampos( lcItPagar );

		lcItPagar.montaTab();
		tabPag.addMouseListener( this );

		txtCodCompra.setVlrInteger( iCodCompra );
		lcCompra.carregaDados();

		setPainel( pinFecha );

		adic( txtCodPlanoPag, 		7,  20,  60, 20, "C�d.pag." );
		adic( txtDescPlanoPag, 	   70,  20, 200, 20, "Descri��o do plano de pagto." );

		adic( txtCodTipoCob, 	  273,  20,  60, 20, "C�d.t.cob." );
		adic( txtDescTipoCob, 	  336,  20, 200, 20, "Descri��o do tipo de cobran�a" );

		adic( txtCodBanco, 			7,  60,  60, 20, "C�d.banc." );
		adic( txtDescBanco, 	   70,  60, 200, 20, "Descri��o do Banco" );

		adic( txtNumConta, 		  273,  60,  60, 20, "Conta" );
		adic( txtDescConta, 	  336,  60, 200, 20, "Descri��o da conta" );
		
		adic( txtCodPlan, 			7, 100,  60, 20, "C�d.Cat." );
		adic( txtDescPlan, 		   70, 100, 200, 20, "Descri��o da categoria" );

		adic( txtCodCC, 		  273, 100,  60, 20, "Cod.CC." );
		adic( txtDescCC, 		  336, 100, 200, 20, "Descri��o do centro de custo" );

		/**********************
		 * ADICIONANDO QUADROS
		 **********************/
		
		adic( pinValores,	 	    7, 140, 326, 110 );
		adic( pinFrete, 		  336, 140, 200, 110 );
		adic( pinTrib, 			    7, 265, 326, 110 );
		adic( pinICMS, 		      336, 265, 200, 110 );
		adic( pinCusto, 		    7, 390, 326, 90 );
		adic( pinImp, 			  336, 390, 200, 90 );
		
		
		/**********************
		 * Quadro de valores
		 **********************/

		pinValores.setBorder( SwingParams.getPanelLabel( "Valores", Color.BLACK ) );
		
		pinValores.adic( txtPercDescCompra,    7, 20, 60, 20, "% Desc." 	);
		pinValores.adic( txtVlrDescCompra, 	  70, 20, 80, 20, "Vlr.Desc." 	);
		pinValores.adic( txtVlrFreteCompra,	 153, 20, 80, 20, "Vlr.Frete" 	);
		pinValores.adic( txtVlrLiqCompra, 	 236, 20, 77, 20, "Vlr.Compra"	);
		pinValores.adic( txtPercAdicCompra,	   7, 60, 60, 20, "% Adic." 	);
		pinValores.adic( txtVlrAdicCompra,	  70, 60, 80, 20, "Vlr.Adic." 	);
		pinValores.adic( txtVlrIPICompra, 	 153, 60, 80, 20, "Vlr.IPI" 	);
		
		/**********************
		 * Quadro Frete
		 **********************/

		pinFrete.setBorder( SwingParams.getPanelLabel( "Frete", Color.BLACK ) );
		
		pinFrete.adic( rgFreteVD, 				7, 10, 178, 30 );
		pinFrete.adic( txtQtdFreteCompra, 		7, 60, 90, 20, "Volumes" );

		/**********************
		 * Quadro Tributa��o
		 **********************/

		pinTrib.setBorder( SwingParams.getPanelLabel( "Tributa��o", Color.BLACK ) );
		
		/**********************
		 * ICMS
		 **********************/

		pinICMS.setBorder( SwingParams.getPanelLabel( "ICMS", Color.BLACK ) );
		
		pinICMS.adic( txtVlrBaseICMS	,   	7, 20, 90, 20, "Base calc." 	);
		pinICMS.adic( txtVlrBaseICMSST	, 	  100, 20, 80, 20, "Base calc. ST" 	);

		pinICMS.adic( txtVlrICMSCompra	,   	7, 60, 90, 20, "Vlr.ICMS" 		);
		pinICMS.adic( txtVlrICMSST		, 	  100, 60, 80, 20, "Vlr.ICMS ST" 	);

		/******************************
		 * Quadro Composi��o do custo
		 ******************************/

		pinCusto.setBorder( SwingParams.getPanelLabel( "Composi��o do custo", Color.BLACK ) );
		
		pinCusto.adic( cbAdicFreteCusto	, 		7, 10, 280, 20 );
		pinCusto.adic( cbAdicAdicCusto	,	 	7, 30, 280, 20 );

		/**********************
		 * Quadro Emiss�o
		 **********************/

		pinImp.setBorder( SwingParams.getPanelLabel( "Emiss�o", Color.BLACK ) );
		
		pinImp.adic( cbEmitePedido				, 7, 0, 180, 20 );
		pinImp.adic( cbEmiteNota				, 7, 20, 180, 20 );
		pinImp.adic( cbFinalizar				, 7, 40, 180, 20 );

		/********** FIM DOS QUADROS ***********/

		if ( txtVlrDescItCompra.getVlrBigDecimal().compareTo( new BigDecimal( 0 ) ) != 0 ) {
			txtPercDescCompra.setAtivo( false );
			txtVlrDescCompra.setAtivo( false );
		}

		tpn.setEnabledAt( 1, false );
		Funcoes.transValor( new BigDecimal( 0 ), 10, 2, false );
		btFechar.addActionListener( this );

		txtPercDescCompra.addFocusListener( this );
		txtVlrDescCompra.addFocusListener( this );
		txtPercAdicCompra.addFocusListener( this );
		txtVlrAdicCompra.addFocusListener( this );

		txtQtdFreteCompra.setVlrBigDecimal( volumes );

		lcCompra.edit();
		
	}

	private void adicVlrFrete() {

		if ( txtVlrFreteCompra.getVlrBigDecimal().intValue() > 0 ) {

			BigDecimal bdVlrFrete = txtVlrFreteCompra.getVlrBigDecimal();
			BigDecimal bdVlrCompra = txtVlrLiqCompra.getVlrBigDecimal();

			if ( Funcoes.mensagemConfirma( null, "Deseja adicionar o valor do frete no valor total?" ) == JOptionPane.YES_OPTION ) {

				txtVlrLiqCompra.setVlrBigDecimal( bdVlrCompra.add( bdVlrFrete ) );
				 
			}
		}
	}

	private void alteraParc() {

		lcItPagar.edit();
		DLFechaPag dl = new DLFechaPag( DLFechaCompra.this, txtVlrParcItPag.getVlrBigDecimal(), txtDtVencItPag.getVlrDate() );
		dl.setVisible( true );
		
		if ( dl.OK ) {
			txtVlrParcItPag.setVlrBigDecimal( (BigDecimal) dl.getValores()[ 0 ] );
			txtDtVencItPag.setVlrDate( (Date) dl.getValores()[ 1 ] );
			lcItPagar.post();
			// Atualiza lcPagar
			if ( lcPagar.getStatus() == ListaCampos.LCS_EDIT ) {
				lcPagar.post(); // Caso o lcPagar estaja como edit executa o post que atualiza
			}
			else {
				lcPagar.carregaDados(); // Caso n�o, atualiza
			}
		}
		else {
			dl.dispose();
			lcItPagar.cancel( false );
		}
		dl.dispose();
	}

	private int getCodPag() {

		int iRetorno = 0;
		String sSQL = "SELECT CODPAG FROM FNPAGAR WHERE CODCOMPRA=? AND CODEMPCP=? AND CODFILIALCP=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement( sSQL );
			ps.setInt( 1, iCodCompraFecha );
			ps.setInt( 2, Aplicativo.iCodEmp );
			ps.setInt( 3, ListaCampos.getMasterFilial( "CPCOMPRA" ) );
			rs = ps.executeQuery();
			if ( rs.next() ) {
				iRetorno = rs.getInt( "CodPag" );
			}

			rs.close();
			ps.close();
			con.commit();
		} catch ( SQLException err ) {
			Funcoes.mensagemErro( this, "Erro ao buscar o c�digo da conta a Pagar!\n" + err.getMessage(), true, con, err );
		}
		return iRetorno;
	}
	
	private Map<String, Integer> getPrefere() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer anocc = null;
		Integer codhistpag = null;

		Map<String, Integer> retorno = new HashMap<String, Integer>();

		try {

			ps = con.prepareStatement( "SELECT ANOCENTROCUSTO,CODHISTPAG FROM SGPREFERE1 WHERE CODEMP=? AND CODFILIAL=?" );
			ps.setInt( 1, Aplicativo.iCodEmp );
			ps.setInt( 2, ListaCampos.getMasterFilial( "SGPREFERE1" ) );

			rs = ps.executeQuery();

			if ( rs.next() ) {
				anocc = rs.getInt( "ANOCENTROCUSTO" );
				codhistpag = rs.getInt( "CODHISTPAG" );
			}

			retorno.put( "codhistrec", codhistpag );
			retorno.put( "anocc", anocc );

			rs.close();
			ps.close();

			con.commit();
		} 
		catch ( SQLException err ) {
			Funcoes.mensagemErro( this, "Erro ao buscar o ano-base para o centro de custo.\n" + err.getMessage(), true, con, err );
		} 
		finally {
			ps = null;
			rs = null;
		}
		return retorno;
	}

	private boolean getVerificaUsu() {

		boolean bRetorno = false;
		String sSQL = "SELECT VERIFALTPARCVENDA FROM SGPREFERE1 WHERE CODEMP=? AND CODFILIAL=?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement( sSQL );
			ps.setInt( 1, Aplicativo.iCodEmp );
			ps.setInt( 2, ListaCampos.getMasterFilial( "SGPREFERE1" ) );
			rs = ps.executeQuery();

			if ( rs.next() ) {
				if ( rs.getString( "VerifAltParcVenda" ).trim().equals( "S" ) ) {
					bRetorno = true;
				}
			}

			rs.close();
			ps.close();
			con.commit();
		} catch ( SQLException err ) {
			Funcoes.mensagemErro( this, "Erro ao carregar a tabela PREFERE1!\n" + err.getMessage(), true, con, err );
		} finally {
			ps = null;
			rs = null;
			sSQL = null;
		}
		return bRetorno;
	}

	public String[] getValores() {

		String[] sRetorno = new String[ 8 ];

		sRetorno[ 0 ] = txtCodPlanoPag.getVlrString();
		sRetorno[ 1 ] = txtVlrDescCompra.getVlrString();
		sRetorno[ 2 ] = txtVlrAdicCompra.getVlrString();
		sRetorno[ 3 ] = cbEmitePedido.getVlrString();
		sRetorno[ 4 ] = cbEmiteNota.getVlrString();
		sRetorno[ 5 ] = txtVlrLiqCompra.getVlrString();
		sRetorno[ 6 ] = txtQtdFreteCompra.getVlrString();
		sRetorno[ 7 ] = cbFinalizar.getVlrString();

		return sRetorno;

	}

	private void fecharCompra() {

		lcCompra.edit();
		if ( txtStatusCompra.getVlrString().trim().equals( "P1" ) ) {
			txtStatusCompra.setVlrString( "P2" );
		}
		if ( txtStatusCompra.getVlrString().trim().equals( "C1" ) ) {
			txtStatusCompra.setVlrString( "C2" );
		}
		lcCompra.post();
		int iCodPag = getCodPag();
		if ( iCodPag > 0 ) {
			txtCodPag.setVlrInteger( new Integer( iCodPag ) );
			lcPagar.carregaDados();
		}
		bPodeSair = true;
	}

	public void actionPerformed( ActionEvent evt ) {

		if ( evt.getSource() == btCancel ) {
			super.actionPerformed( evt );
		}
		else if ( evt.getSource() == btOK ) {
			if ( bPodeSair ) {
				if ( lcPagar.getStatus() == ListaCampos.LCS_EDIT ) {
					lcPagar.post();
				}
				if ( cbEmitePedido.getVlrString().trim().equals( "S" ) ) {
					lcCompra.edit();
					txtStatusCompra.setVlrString( "P3" );
					if ( !lcCompra.post() ) {
						cbEmitePedido.setVlrString( "N" );
					}
				}
				if ( cbEmiteNota.getVlrString().trim().equals( "S" ) ) {
					lcCompra.edit();
					txtStatusCompra.setVlrString( "C3" );
					if ( !lcCompra.post() ) {
						cbEmiteNota.setVlrString( "N" );
					}
				}
				super.actionPerformed( evt );
			}
			else {
				if ( tpn.getSelectedIndex() == 0 ) {
					fecharCompra();
					tpn.setEnabledAt( 1, true );
					tpn.setSelectedIndex( 1 );
				}
			}
		}
		else if ( evt.getSource() == btFechar ) {
			fecharCompra();
			btOK.doClick();
		}
	}

	public void focusLost( FocusEvent fevt ) {

		if ( fevt.getSource() == txtPercDescCompra ) {
			if ( txtPercDescCompra.getText().trim().length() < 1 ) {
				txtVlrDescCompra.setAtivo( true );
			}
			else {
				txtVlrDescCompra.setVlrBigDecimal( txtVlrProdCompra.getVlrBigDecimal().multiply( txtPercDescCompra.getVlrBigDecimal() ).divide( new BigDecimal( "100" ), 2, BigDecimal.ROUND_HALF_UP ) );
				txtVlrDescCompra.setAtivo( false );
			}
		}
		if ( fevt.getSource() == txtPercAdicCompra ) {
			if ( txtPercAdicCompra.getText().trim().length() < 1 ) {
				txtVlrAdicCompra.setAtivo( true );
			}
			else {
				txtVlrAdicCompra.setVlrBigDecimal( txtVlrProdCompra.getVlrBigDecimal().multiply( txtPercAdicCompra.getVlrBigDecimal() ).divide( new BigDecimal( "100" ), 2, BigDecimal.ROUND_HALF_UP ) );
				txtVlrAdicCompra.setAtivo( false );
			}
		}
	}

	public void focusGained( FocusEvent fevt ) {

		if ( fevt.getSource() == txtVlrDescCompra ) {
			BigDecimal liq = txtVlrLiqCompra.getVlrBigDecimal();
			BigDecimal des = txtVlrDescCompra.getVlrBigDecimal();
			BigDecimal tot = liq.subtract( des );
			txtVlrLiqCompra.setVlrBigDecimal( tot );
		}
	}

	public void keyPressed( KeyEvent kevt ) {

		if ( kevt.getSource() == txtVlrFreteCompra ) {
			if ( kevt.getKeyCode() == KeyEvent.VK_ENTER ) {
				adicVlrFrete();
			}
		}

		super.keyPressed( kevt );
	}

	public void mouseClicked( MouseEvent mevt ) {

		if ( ( mevt.getClickCount() == 2 ) && ( tabPag.getLinhaSel() >= 0 ) ) {
			if ( getVerificaUsu() ) {
				FPassword fpw = new FPassword( this, FPassword.ALT_PARC_VENDA, null, con );
				fpw.execShow();
				if ( fpw.OK ) {
					alteraParc();
				}
				fpw.dispose();
			}
			else {
				alteraParc();
			}
		}
	}

	public void mouseEntered( MouseEvent e ) {

	}

	public void mouseExited( MouseEvent e ) {

	}

	public void mousePressed( MouseEvent e ) {

	}

	public void mouseReleased( MouseEvent e ) {

	}
}

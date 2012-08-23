/**
 * @version 14/07/2003 <BR>
 * @author Setpoint Inform�tica Ltda./Fernando Oliveira da Silva <BR>
 * 
 * Projeto: Freedom <BR>
 * 
 * Pacote: org.freedom.modulos.std <BR>
 * Classe:
 * @(#)DLFechaCompra.java <BR>
 * 
 * Este programa � licenciado de acordo com a LPG-PC (Licen�a P�blica Geral para Programas de Computador), <BR>
 * vers�o 2.1.0 ou qualquer vers�o posterior. <BR>
 * A LPG-PC deve acompanhar todas PUBLICA��ES, DISTRIBUI��ES e REPRODU��ES deste Programa. <BR>
 * Caso uma c�pia da LPG-PC n�o esteja dispon�vel junto com este Programa, voc� pode contatar <BR>
 * o LICENCIADOR ou ent�o pegar uma c�pia em: <BR>
 * Licen�a: http://www.lpg.adv.br/licencas/lpgpc.rtf <BR>
 * Para poder USAR, PUBLICAR, DISTRIBUIR, REPRODUZIR ou ALTERAR este Programa � preciso estar <BR>
 * de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 * Coment�rios sobre a classe...
 */

package org.freedom.modulos.std;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.swing.JScrollPane;

import org.freedom.componentes.GuardaCampo;
import org.freedom.componentes.JCheckBoxPad;
import org.freedom.componentes.JLabelPad;
import org.freedom.componentes.JPanelPad;
import org.freedom.componentes.JRadioGroup;
import org.freedom.componentes.JTabbedPanePad;
import org.freedom.componentes.JTextFieldFK;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.componentes.Tabela;
import org.freedom.funcoes.Funcoes;
import org.freedom.telas.Aplicativo;
import org.freedom.telas.FFDialogo;
import org.freedom.telas.FPassword;

public class DLFechaCompra extends FFDialogo implements FocusListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private JTabbedPanePad tpn = new JTabbedPanePad();

	private JPanelPad pinFecha = new JPanelPad( 420, 300 );

	private JPanelPad pnPagar = new JPanelPad( JPanelPad.TP_JPANEL, new BorderLayout() );

	private Tabela tabPag = new Tabela();

	private JTextFieldPad txtCodCompra = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtVlrDescItCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 3 );

	private JTextFieldPad txtPercDescCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 6, 2 );

	private JTextFieldPad txtVlrDescCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 3 );

	private JTextFieldPad txtPercAdicCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 6, 2 );

	private JTextFieldPad txtVlrLiqCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 3 );

	private JTextFieldPad txtVlrAdicCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 3 );

	private JTextFieldPad txtVlrProdCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 3 );

	private JTextFieldPad txtVlrFreteCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 3 );

	private JTextFieldPad txtVlrICMSCompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 3 );

	private JTextFieldPad txtVlrIPICompra = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 3 );

	private JTextFieldPad txtCodPlanoPag = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtCodPag = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtCodBanco = new JTextFieldPad( JTextFieldPad.TP_STRING, 3, 0 );

	private JTextFieldPad txtNParcPag = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtVlrParcItPag = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 3 );

	private JTextFieldPad txtVlrParcPag = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 3 );

	private JTextFieldPad txtDtVencItPag = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );

	private JTextFieldPad txtStatusCompra = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );

	private JTextFieldFK txtDescPlanoPag = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );

	private JTextFieldFK txtDescBanco = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );

	private JCheckBoxPad cbImpPed = new JCheckBoxPad( "Imprime Pedido?", "S", "N" );

	private JCheckBoxPad cbImpNot = new JCheckBoxPad( "Imprime Nota?", "S", "N" );

	private JCheckBoxPad cbAdicFrete = new JCheckBoxPad( "adiciona valor do frete ao custo?", "S", "N" );

	private JRadioGroup rgFreteVD = null;

	private ListaCampos lcCompra = new ListaCampos( this );

	private ListaCampos lcPlanoPag = new ListaCampos( this, "PG" );

	private ListaCampos lcPagar = new ListaCampos( this );

	private ListaCampos lcBanco = new ListaCampos( this, "BO" );

	private ListaCampos lcItPagar = new ListaCampos( this );

	private Vector vVals = new Vector();

	private Vector vLabs = new Vector();

	private int iCodCompraFecha = 0;

	private boolean bPodeSair = false;

	public DLFechaCompra( Connection cn, Integer iCodCompra, Component cOrig ) {

		super( cOrig );
		setConexao( cn );
		iCodCompraFecha = iCodCompra.intValue();
		setTitulo( "Fechar Compra" );
		setAtribos( 440, 370 );

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
		rgFreteVD = new JRadioGroup( 1, 2, vLabs, vVals );

		txtCodPlanoPag.setNomeCampo( "CodPlanoPag" );
		lcPlanoPag.add( new GuardaCampo( txtCodPlanoPag, "CodPlanoPag", "C�d.p.pg.", ListaCampos.DB_PK, false ) );
		lcPlanoPag.add( new GuardaCampo( txtDescPlanoPag, "DescPlanoPag", "Descri��o do plano de pagamento", ListaCampos.DB_SI, false ) );
		txtCodPlanoPag.setTabelaExterna( lcPlanoPag );
		txtCodPlanoPag.setFK( true );
		txtDescPlanoPag.setListaCampos( lcPlanoPag );
		lcPlanoPag.montaSql( false, "PLANOPAG", "FN" );
		lcPlanoPag.setQueryCommit( false );
		lcPlanoPag.setReadOnly( true );
		lcPlanoPag.setConexao( cn );

		txtCodBanco.setNomeCampo( "CodBanco" );
		lcBanco.add( new GuardaCampo( txtCodBanco, "CodBanco", "C�d.banco", ListaCampos.DB_PK, false ) );
		lcBanco.add( new GuardaCampo( txtDescBanco, "NomeBanco", "Nome do banco", ListaCampos.DB_SI, false ) );
		txtDescBanco.setListaCampos( lcBanco );
		lcBanco.montaSql( false, "BANCO", "FN" );
		lcBanco.setQueryCommit( false );
		lcBanco.setReadOnly( true );
		lcBanco.setConexao( cn );
		txtCodBanco.setTabelaExterna( lcBanco );
		txtCodBanco.setFK( true );

		txtCodCompra.setNomeCampo( "CodCompra" );
		txtCodPlanoPag.setTabelaExterna( lcPlanoPag );
		lcCompra.add( new GuardaCampo( txtCodCompra, "CodCompra", "N.pedido", ListaCampos.DB_PK, false ) );
		lcCompra.add( new GuardaCampo( txtCodPlanoPag, "CodPlanoPag", "Cod.p.pg.", ListaCampos.DB_FK, txtDescPlanoPag, false ) );
		lcCompra.add( new GuardaCampo( txtVlrLiqCompra, "VlrLiqCompra", "V.compra", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrICMSCompra, "VlrICMSCompra", "V.ICMS", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrIPICompra, "VlrIPICompra", "V.IPI", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrDescItCompra, "VlrDescItCompra", "% Desc.it.", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrDescCompra, "VlrDescCompra", "% Desc.it.", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrAdicCompra, "VlrAdicCompra", "V.adic.", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrProdCompra, "VlrProdCompra", "V.prod.", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtVlrFreteCompra, "VlrFreteCompra", "V.prod.", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtStatusCompra, "StatusCompra", "Status", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( txtCodBanco, "CodBanco", "CodBanco", ListaCampos.DB_FK, txtDescBanco, false ) );
		lcCompra.add( new GuardaCampo( rgFreteVD, "TipoFreteCompra", "Tipo do frete", ListaCampos.DB_SI, false ) );
		lcCompra.add( new GuardaCampo( cbAdicFrete, "AdicFreteCompra", "frete na campra", ListaCampos.DB_SI, false ) );
		lcCompra.montaSql( false, "COMPRA", "CP" );
		lcCompra.setConexao( cn );
		txtVlrLiqCompra.setListaCampos( lcCompra );
		txtVlrICMSCompra.setListaCampos( lcCompra );
		txtVlrIPICompra.setListaCampos( lcCompra );
		txtVlrAdicCompra.setListaCampos( lcCompra );
		txtPercAdicCompra.setListaCampos( lcCompra );
		txtVlrDescCompra.setListaCampos( lcCompra );
		txtVlrFreteCompra.setListaCampos( lcCompra );
		txtPercDescCompra.setListaCampos( lcCompra );
		txtStatusCompra.setListaCampos( lcCompra );
		txtCodPlanoPag.setListaCampos( lcCompra );

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
		adic( new JLabelPad( "C�d.p.pag." ), 7, 0, 270, 20 );
		adic( txtCodPlanoPag, 7, 20, 80, 20 );
		adic( new JLabelPad( "Descri��o do plano de pagto." ), 90, 0, 270, 20 );
		adic( txtDescPlanoPag, 90, 20, 270, 20 );
		adic( new JLabelPad( "% Desc." ), 7, 40, 100, 20 );
		adic( txtPercDescCompra, 7, 60, 100, 20 );
		adic( new JLabelPad( "V Desc." ), 110, 40, 97, 20 );
		adic( txtVlrDescCompra, 110, 60, 97, 20 );
		adic( new JLabelPad( "% Adic." ), 210, 40, 97, 20 );
		adic( txtPercAdicCompra, 210, 60, 97, 20 );
		adic( new JLabelPad( "V Adic." ), 310, 40, 100, 20 );
		adic( txtVlrAdicCompra, 310, 60, 100, 20 );
		adic( new JLabelPad( "V Frete." ), 7, 80, 100, 20 );
		adic( txtVlrFreteCompra, 7, 100, 100, 20 );
		adic( new JLabelPad( "V. Compra" ), 110, 80, 100, 20 );
		adic( txtVlrLiqCompra, 110, 100, 97, 20 );
		adic( new JLabelPad( "V. ICMS" ), 210, 80, 100, 20 );
		adic( txtVlrICMSCompra, 210, 100, 97, 20 );
		adic( new JLabelPad( "V. IPI" ), 310, 80, 100, 20 );
		adic( txtVlrIPICompra, 310, 100, 100, 20 );
		adic( new JLabelPad( "Tipo frete" ), 7, 120, 100, 20 );
		adic( rgFreteVD, 7, 140, 160, 30 );
		adic( cbAdicFrete, 200, 145, 300, 20 );
		adic( new JLabelPad( "C�d.banco" ), 7, 175, 100, 20 );
		adic( txtCodBanco, 7, 195, 100, 20 );
		adic( new JLabelPad( "Descri��o do Banco" ), 110, 175, 200, 20 );
		adic( txtDescBanco, 110, 195, 300, 20 );
		adic( cbImpPed, 7, 225, 180, 20 );
		adic( cbImpNot, 200, 225, 180, 20 );

		if ( txtVlrDescItCompra.getVlrString().length() > 0 ) {
			txtPercDescCompra.setAtivo( false );
			txtVlrDescCompra.setAtivo( false );
		}

		tpn.setEnabledAt( 1, false );

		txtPercDescCompra.addFocusListener( this );
		txtVlrDescCompra.addFocusListener( this );
		txtPercAdicCompra.addFocusListener( this );
		txtVlrAdicCompra.addFocusListener( this );

		lcCompra.edit();
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
			if ( !con.getAutoCommit() ) {
				con.commit();
			}
		} catch ( SQLException err ) {
			Funcoes.mensagemErro( this, "Erro ao buscar o c�digo da conta a Pagar!\n" + err.getMessage(), true, con, err );
		}
		return iRetorno;
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
			if ( !con.getAutoCommit() ) {
				con.commit();
			}
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

		String[] sRetorno = new String[ 6 ];
		sRetorno[ 0 ] = txtCodPlanoPag.getVlrString();
		sRetorno[ 1 ] = txtVlrDescCompra.getVlrString();
		sRetorno[ 2 ] = txtVlrAdicCompra.getVlrString();
		sRetorno[ 3 ] = cbImpPed.getVlrString();
		sRetorno[ 4 ] = cbImpNot.getVlrString();
		sRetorno[ 5 ] = txtVlrLiqCompra.getVlrString();
		return sRetorno;
	}

	public void actionPerformed( ActionEvent evt ) {

		if ( evt.getSource() == btCancel ) {
			super.actionPerformed( evt );
		}
		else if ( bPodeSair ) {
			if ( evt.getSource() == btOK ) {
				if ( lcPagar.getStatus() == ListaCampos.LCS_EDIT ) {
					lcPagar.post();
				}
				if ( cbImpPed.getVlrString().trim().equals( "S" ) ) {
					lcCompra.edit();
					txtStatusCompra.setVlrString( "P3" );
					if ( !lcCompra.post() ) {
						cbImpPed.setVlrString( "N" );
					}
				}
				if ( cbImpNot.getVlrString().trim().equals( "S" ) ) {
					lcCompra.edit();
					txtStatusCompra.setVlrString( "C3" );
					if ( !lcCompra.post() ) {
						cbImpNot.setVlrString( "N" );
					}
				}
			}
			super.actionPerformed( evt );
		}
		if ( evt.getSource() == btOK ) {
			if ( tpn.getSelectedIndex() == 0 ) {
				lcCompra.edit();
				if ( txtStatusCompra.getVlrString().trim().equals( "P1" ) ) {
					txtStatusCompra.setVlrString( "P2" );
				}
				if ( txtStatusCompra.getVlrString().trim().equals( "C1" ) ) {
					txtStatusCompra.setVlrString( "C2" );
				}
				lcCompra.post();
				tpn.setEnabledAt( 1, true );
				tpn.setSelectedIndex( 1 );
				int iCodPag = getCodPag();
				if ( iCodPag > 0 ) {
					txtCodPag.setVlrInteger( new Integer( iCodPag ) );
					lcPagar.carregaDados();
				}
				bPodeSair = true;
			}
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

	public void focusGained( FocusEvent fevt ) { }

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

	public void mouseEntered( MouseEvent e ) { }

	public void mouseExited( MouseEvent e ) { }

	public void mousePressed( MouseEvent e ) { }

	public void mouseReleased( MouseEvent e ) { }
}
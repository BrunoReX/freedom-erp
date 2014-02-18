package org.freedom.persist.entity;

// Generated 18/02/2014 15:53:28 by Hibernate Tools 4.0.0

/**
 * TkcampanhacliId generated by hbm2java
 */
public class TkcampanhacliId implements java.io.Serializable {

	private String codcamp;
	private short codfilial;
	private int codemp;
	private int codcli;
	private short codfilialcl;
	private int codempcl;

	public TkcampanhacliId() {
	}

	public TkcampanhacliId(String codcamp, short codfilial, int codemp,
			int codcli, short codfilialcl, int codempcl) {
		this.codcamp = codcamp;
		this.codfilial = codfilial;
		this.codemp = codemp;
		this.codcli = codcli;
		this.codfilialcl = codfilialcl;
		this.codempcl = codempcl;
	}

	public String getCodcamp() {
		return this.codcamp;
	}

	public void setCodcamp(String codcamp) {
		this.codcamp = codcamp;
	}

	public short getCodfilial() {
		return this.codfilial;
	}

	public void setCodfilial(short codfilial) {
		this.codfilial = codfilial;
	}

	public int getCodemp() {
		return this.codemp;
	}

	public void setCodemp(int codemp) {
		this.codemp = codemp;
	}

	public int getCodcli() {
		return this.codcli;
	}

	public void setCodcli(int codcli) {
		this.codcli = codcli;
	}

	public short getCodfilialcl() {
		return this.codfilialcl;
	}

	public void setCodfilialcl(short codfilialcl) {
		this.codfilialcl = codfilialcl;
	}

	public int getCodempcl() {
		return this.codempcl;
	}

	public void setCodempcl(int codempcl) {
		this.codempcl = codempcl;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TkcampanhacliId))
			return false;
		TkcampanhacliId castOther = (TkcampanhacliId) other;

		return ((this.getCodcamp() == castOther.getCodcamp()) || (this
				.getCodcamp() != null && castOther.getCodcamp() != null && this
				.getCodcamp().equals(castOther.getCodcamp())))
				&& (this.getCodfilial() == castOther.getCodfilial())
				&& (this.getCodemp() == castOther.getCodemp())
				&& (this.getCodcli() == castOther.getCodcli())
				&& (this.getCodfilialcl() == castOther.getCodfilialcl())
				&& (this.getCodempcl() == castOther.getCodempcl());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCodcamp() == null ? 0 : this.getCodcamp().hashCode());
		result = 37 * result + this.getCodfilial();
		result = 37 * result + this.getCodemp();
		result = 37 * result + this.getCodcli();
		result = 37 * result + this.getCodfilialcl();
		result = 37 * result + this.getCodempcl();
		return result;
	}

}

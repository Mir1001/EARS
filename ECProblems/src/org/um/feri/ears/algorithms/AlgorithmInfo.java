package org.um.feri.ears.algorithms;

public class AlgorithmInfo {
	private String publishedAcronym; //from papers (ES(1,1), PSO, DE, ...)
	private String paperBib; //latex bib format name of the original published paper
	private String versionAcronym; //if you made some semantic changes compared to original
	private String versionDescription; //what is different from original
	
	public AlgorithmInfo(String publishedName, String paperBib,
			String versionName, String versionDescription) {
		super();
		this.publishedAcronym = publishedName;
		this.paperBib = paperBib;
		this.versionAcronym = versionName;
		this.versionDescription = versionDescription;
	}
	
	/**
	 * Version name needs to be similar to publishedName name.
	 * For example: publishedName PSO my name PSO+ or PSOS, ...
	 * 
	 * @param versionAcronym
	 */
	public AlgorithmInfo(String versionName) {
		super(); 
		this.versionAcronym = versionName;
	}
	public String getPublishedAcronym() {
		return publishedAcronym;
	}
	public void setPublishedAcronym(String publishedAcronym) {
		this.publishedAcronym = publishedAcronym;
	}
	public String getPaperBib() {
		return paperBib;
	}
	public void setPaperBib(String paperBib) {
		this.paperBib = paperBib;
	}
	public String getVersionAcronym() {
		return versionAcronym;
	}
	public void setVersionAcronym(String versionName) {
		this.versionAcronym = versionName;
	}
	public String getVersionDescription() {
		return versionDescription;
	}
	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}
	
}

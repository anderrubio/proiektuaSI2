package domain;

public class ApustuEginKlaseLag {
	private Double balioa;
	private Integer apustuBikoitzaGalarazi;
	
	public ApustuEginKlaseLag(Double balioa, Integer apustuBikoitzaGalarazi) {
		this.balioa=balioa;
		this.apustuBikoitzaGalarazi=apustuBikoitzaGalarazi;
	}

	public Double getBalioa() {
		return balioa;
	}

	public void setBalioa(Double balioa) {
		this.balioa = balioa;
	}

	public Integer getApustuBikoitzaGalarazi() {
		return apustuBikoitzaGalarazi;
	}

	public void setApustuBikoitzaGalarazi(Integer apustuBikoitzaGalarazi) {
		this.apustuBikoitzaGalarazi = apustuBikoitzaGalarazi;
	}
}

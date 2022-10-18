package domain;

public class DiruKudeatzailea {
	
	private Double dirua;
	private String mota;
	
	public DiruKudeatzailea(Double dirua, String mota) {
		this.dirua = dirua;
		this.mota = mota;
	}

	public Double getDirua() {
		return dirua;
	}

	public void setDirua(Double dirua) {
		this.dirua = dirua;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

}
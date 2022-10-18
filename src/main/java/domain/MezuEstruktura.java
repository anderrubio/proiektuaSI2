package domain;




public class MezuEstruktura{
	private String testua;
	private String titulo;
	
	public MezuEstruktura(String testua, String titulo) {
		this.testua = testua;
		this.titulo = titulo;
	}

	public String getTestua() {
		return testua;
	}

	public void setTestua(String testua) {
		this.testua = testua;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}

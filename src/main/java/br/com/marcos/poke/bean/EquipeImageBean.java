package br.com.marcos.poke.bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.marcos.poke.util.Settings;

@ManagedBean
@RequestScoped
public class EquipeImageBean {
	
	 public StreamedContent getImg() throws IOException {
		StreamedContent img = null;
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }else {
            String imageId = context.getExternalContext().getRequestParameterMap().get("parCodigo");
            File f = new File(Settings.getCaminho() + "img/treinador/" + imageId+".png");
			if(f.isFile()){
				Path path = Paths.get(f.getAbsolutePath());
				InputStream stream = Files.newInputStream(path);
				img = new DefaultStreamedContent(stream);
			}else{
				Path path = Paths.get(Settings.getCaminho() + "img/default/unknown.png");
				InputStream stream = Files.newInputStream(path);
				img = new DefaultStreamedContent(stream);
			}
            return img;
        }
    }

}
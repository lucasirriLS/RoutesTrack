package it.r2014.routetrack.map;

import java.io.Serializable;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
 
public class EventView implements Serializable {
	
	private int zoom = 6;
	private double  latitudeStart= 42.14000; // 37.13 ; 
	private double  longitudeStart= 11.8300 ; //22.43;
	private MapModel simpleModel;
	private Integer refresh = 10;
	private Marker marker;
	
	@PostConstruct
	public void init() {
		
		simpleModel = new DefaultMapModel();
		popolateModel(simpleModel);
		
	}
	
	
	public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
       
    }
      
	private void popolateModel(MapModel model) {
		
		int Low = 10;
		int High = 100;
		int  inclat ; // = (rn.nextInt(High-Low) + Low) 
		int  inclon ; // = (rn.nextInt(High-Low) + Low) 
		Random rn = new Random();
		Marker marker ;
		for (int i = 0; i < 500; i++) {
			rn = new Random();
			inclat = (rn.nextInt(High-Low) + Low) ;
			
			rn = new Random();
			inclon = (rn.nextInt(High-Low) + Low) ;
			int fattore = -1;
			
			if ( (rn.nextInt() & 1) == 0 ) fattore = -1 ; 
			else  fattore = 1 ;

			marker = new Marker(new LatLng(latitudeStart  + (0.1*inclat*fattore), longitudeStart + (0.1*inclon*fattore)), "Route num " + i);
			marker.setData("Referente: <b>Gino Paoli</b> - tel 333.6543211");
			
			marker.setIcon(getMarkerPath(i));
			model.addOverlay(marker);
		}
	
		// San Rossore 43.66731, 10.34234
		marker = new Marker(new LatLng(43.66731, 10.34234), "Ran Rossore");
		
		marker.setIcon("../resurces/markers/flag_finish.png");
		model.addOverlay(marker);
	}
	
	private String getMarkerPath(int i) {
		
		String path = "../resurces/markers/flag_1.png";
		
		if(i<9){
			path = "../resurces/markers/flag_" + i + ".png";
		} else {
			path = "../resurces/markers/flag_" + (i%8) + ".png";
		}
		// TODO Auto-generated method stub
		return path;
	}
	
	public void reloadModel(){
		riGenerateModel();
	}
	
	public void testPrint(){
		System.out.println("EventView.testPrint() ------------------------------ ");
	}
	
	public String riGenerateModel(){
		simpleModel = new DefaultMapModel();
		popolateModel(this.simpleModel);
		return null;
	}
     
    public void onStateChange(StateChangeEvent event) {
        int zoomLevel = event.getZoomLevel();
        zoom = zoomLevel;
        latitudeStart = event.getCenter().getLat();
        longitudeStart = event.getCenter().getLng();
    }
      
    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
    }
      
    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public double getLatitudeStart() {
		return latitudeStart;
	}

	public void setLatitudeStart(double latitudeStart) {
		this.latitudeStart = latitudeStart;
	}

	public double getLongitudeStart() {
		return longitudeStart;
	}

	public void setLongitudeStart(double longitudeStart) {
		this.longitudeStart = longitudeStart;
	}

	public MapModel getSimpleModel() {
		simpleModel = new DefaultMapModel();
		popolateModel(simpleModel);
		return simpleModel;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public Integer getRefresh() {
		return refresh;
	}

	public void setRefresh(Integer refresh) {
		this.refresh = refresh;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}
}
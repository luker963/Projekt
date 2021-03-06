package org.climbingguide.gui;

import java.util.ArrayList;
import java.util.List;

import org.climbingguide.dao.RouteDao;

import org.climbingguide.model.Route;


import android.os.Bundle;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.climbingguide.main.R;

public class FragmentRoutes extends ListFragment{

	private RouteDao  getRoutes;
	private List<Route> routeList = new ArrayList<Route>();
	private Route route = new Route();
	private int idOfSector;
	  @Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    getActivity().setTitle("Routes");
	    
	    getRoutes = new RouteDao(getActivity());  	
	    idOfSector = getArguments().getInt("idOfSector");
	    

		
	    getRoutes.open();
	
	    routeList = getRoutes.getRoute(idOfSector); //get by sector

    	getRoutes.close();
    	

    	ArrayAdapter<Route> adapter = new ArrayAdapter<Route>(getActivity(),android.R.layout.simple_list_item_1,routeList);

	    setListAdapter(adapter);
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	
		  	
		  	Bundle bundle = new Bundle();

		  	route = routeList.get(position);
		  	
		  	bundle.putString("name",route.getName());
		  	bundle.putString("difficulty",route.getDificulty());
		  	bundle.putInt("bolts",route.getBolts());
		  	bundle.putInt("length",route.getLength());
		  	
		  	bundle.putDouble("longitude",route.getLongitude());
		  	bundle.putDouble("latitude",route.getLatitute());
		  	
		  	//Toast.makeText(getActivity(), "Bundle"+bundle.toString(), Toast.LENGTH_LONG).show();
		  	
			FragmentRoute fragobj = new FragmentRoute();
			fragobj.setArguments(bundle);
		  
	
		  FragmentTransaction transaction = getFragmentManager().beginTransaction();
		  
		  transaction.replace(R.id.frame_container, fragobj,"Route");
		  transaction.addToBackStack(null);
		  
		  transaction.commit();		  
	  }		  
	
	  public int getIdOfSector()
	  {
//		  int i;
//		  if(routeList.isEmpty())
//		  {
//			  i = -1;
//		  }
//		  else
//		  {
//			  route = routeList.get(0);
//			  i = route.getIdOfSector();
//		  }
		 return idOfSector;
	  }
}

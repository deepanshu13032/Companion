package com.example.deepanshuarora.companion;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;

import java.util.ArrayList;

public class MapsActivity extends ActionBarActivity implements OnMapReadyCallback,View.OnClickListener, DirectionCallback {

    // Buttons
    private Button btnRequestDirection,showpop;



    //Google Map Variables
    private String serverKey = "AIzaSyDaop96PQJGwmGPeZFbqlXg_iVLdBSHu7w";
    private LatLng origin = new LatLng(28.547291, 77.273190);
    private LatLng destination;
    private GoogleMap mMap;


    // Toolbar Variables
    private Toolbar toolbar;

    // Menu Varibles
    private Menu menu;


    // QuickAction Menu Varibles
    private static final int ID_UP     = 1;
    private static final int ID_DOWN   = 2;
    private static final int ID_SEARCH = 3;
    private static final int ID_INFO   = 4;
    private static final int ID_ERASE  = 5;
    private static final int ID_OK     = 6;
    private double lt,lg;
    private double def = 12.343242;
    private String Destination_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent reci = this.getIntent();
        lt = reci.getDoubleExtra("lati",def);
        lg = reci.getDoubleExtra("long", def);
        Destination_name = reci.getStringExtra("name");
        destination = new LatLng(lt,lg);
        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        // Quick Pop Up
        ActionItem nextItem     = new ActionItem(ID_DOWN, "Next", getResources().getDrawable(R.drawable.menu_down_arrow));
        ActionItem prevItem     = new ActionItem(ID_UP, "Prev", getResources().getDrawable(R.drawable.menu_up_arrow));
        ActionItem searchItem   = new ActionItem(ID_SEARCH, "Find", getResources().getDrawable(R.drawable.menu_search));
        ActionItem infoItem     = new ActionItem(ID_INFO, "Info", getResources().getDrawable(R.drawable.menu_info));
        ActionItem eraseItem    = new ActionItem(ID_ERASE, "Clear", getResources().getDrawable(R.drawable.menu_eraser));
        ActionItem okItem       = new ActionItem(ID_OK, "OK", getResources().getDrawable(R.drawable.menu_ok));

        //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
        prevItem.setSticky(true);
        nextItem.setSticky(true);

        //create QuickAction. Use QuickAction.VERTICAL or QuickAction.HORIZONTAL param to define layout
        //orientation
        final QuickAction quickAction = new QuickAction(this, QuickAction.HORIZONTAL);

        //add action items into QuickAction
        quickAction.addActionItem(nextItem);
        quickAction.addActionItem(prevItem);
        quickAction.addActionItem(searchItem);
        quickAction.addActionItem(infoItem);
        quickAction.addActionItem(eraseItem);
        quickAction.addActionItem(okItem);

        //Set listener for action item clicked
        quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                //here we can filter which action item was clicked with pos or actionId parameter
                ActionItem actionItem = quickAction.getActionItem(pos);

                Toast.makeText(getApplicationContext(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
            }
        });

        //set listnener for on dismiss event, this listener will be called only if QuickAction dialog was dismissed
        //by clicking the area outside the dialog.
        quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(getApplicationContext(), "Dismissed", Toast.LENGTH_SHORT).show();
            }
        });
        showpop = (Button) findViewById(R.id.mButtonFeed);
        showpop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quickAction.show(v);
            }
        });


        // ALl the buttons on the Maps Activity including the toolbar buttons btnrequestdirection is placed at bottom of screen for map and showpop is thhe button for menu pop up
        btnRequestDirection = (Button) findViewById(R.id.btn_request_direction);
        btnRequestDirection.setOnClickListener(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    // MENU CREATION AND CLICK HANDLING

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_mapsactivity, menu);
        this.menu = menu;

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    //  MAP FUNCTIONS START HERE
    ///
    ///
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
<<<<<<< HEAD
        mMap.addMarker(new MarkerOptions().position(origin).title("Marker at origin"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));
=======
        mMap.addMarker(new MarkerOptions().position(destination).title(Destination_name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(destination));
>>>>>>> karandev
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin, 13));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=  PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    public void requestDirection() {
        Snackbar.make(btnRequestDirection, "Direction Requesting...", Snackbar.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(serverKey)
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.DRIVING)
                .execute(this);
    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        Snackbar.make(btnRequestDirection, "Success with status : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        if (direction.isOK()) {
            mMap.addMarker(new MarkerOptions().position(origin));
            mMap.addMarker(new MarkerOptions().position(destination));
            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            mMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.RED));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(destination));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 13));
            btnRequestDirection.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Snackbar.make(btnRequestDirection, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }


    ///
    ///
    // MAP FUNCTIONS END HERE!!!

    // Dialog Show Function

    public void showDialog(Context context, int x, int y){
        Dialog dialog  = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.filterdialog_layout);
        dialog.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.x = x;
        wmlp.y = y;
        dialog.show();
    }

    ///
    //
    //QUICK POP UP FUNCTION
    public void  AddQuickPopup(){

    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_request_direction){
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin,10));
        requestDirection();
        }
        if (id == R.id.mButtonFeed){

                showDialog(this, v.getLeft() - (v.getWidth() * 2),
                        v.getTop() + (v.getHeight() * 2));

        }

        }



}


//TODO Some Waste Lines of Code written down here - Can be used if needed and ignored if not



//Button v = (Button) MenuItemCompat.getActionView(menu.findItem(R.id.mButtonFeed));





/*GMapV2Direction md = new GMapV2Direction();
        Document doc = md.getDocument(new LatLng(28.6895479,77.1265842), new LatLng(28.673751,77.1273376),
                GMapV2Direction.MODE_DRIVING);
        ArrayList<LatLng> directionPoint = md.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(3).color(
                Color.RED);

        for (int i = 0; i < directionPoint.size(); i++) {
            rectLine.add(directionPoint.get(i));
        }
        Polyline polylin = mMap.addPolyline(rectLine);

        GoogleDirection.withServerKey(serverKey)
                .from(new LatLng(28.6895479, 77.1265842))
                .to(new LatLng(28.673751, 77.1273376))
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        String status = direction.getStatus();
                            Log.d("Deep", "onDirectionSuccess: " + status );

                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                        // Do something
                    }
                });*/
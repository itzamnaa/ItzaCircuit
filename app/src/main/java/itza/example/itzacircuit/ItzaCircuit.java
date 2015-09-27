package itza.example.itzacircuit;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;
import java.io.File;
import itza.example.itzacircuit.Control.Save;
import itza.example.itzacircuit.Control.ui;
import itza.example.itzacircuit.Elements.Emat;
import itza.example.itzacircuit.Elements.List;
import itza.example.itzacircuit.Nodo.NetList;


public class ItzaCircuit extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private static final PlaceholderFragment phf=PlaceholderFragment.newInstance(0);
    public static ViewFlipper vf=null;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        ui.context=getApplicationContext();
        ui.activity=this;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container,phf)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.itza_circuit, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rv = inflater.inflate(R.layout.fragment, container, false);
            ui.valuein=(EditText)rv.findViewById(R.id.valuein);
            ui.buttonsflipper =(ViewFlipper)rv.findViewById(R.id.viewFlipper);
            ui.viewswitcher =(ViewSwitcher)rv.findViewById(R.id.viewSwitcher2);
            ui.baddcolumn=(ImageButton)rv.findViewById(R.id.addcolumn);
            ui.baddrow=(ImageButton)rv.findViewById(R.id.addrow);
            ui.bdeletecolumn=(ImageButton)rv.findViewById(R.id.deletecolumn);
            ui.bdeleterow=(ImageButton)rv.findViewById(R.id.deleterow);
            ui.tabla=(TableLayout) rv.findViewById(R.id.tabla);
            ui.bresis=(ImageButton)rv.findViewById(R.id.bresis);
            ui.bvacio=(ImageButton)rv.findViewById(R.id.bvacio);
            ui.bfuente=(ImageButton)rv.findViewById(R.id.bfuente);
            ui.btierra=(ImageButton)rv.findViewById(R.id.btierra);
            ui.bconector=(ImageButton)rv.findViewById(R.id.bonector);
            ui.nameview=(TextView)rv.findViewById(R.id.nameview);
            ui.infoview=(TextView)rv.findViewById(R.id.infoview);
            ui.nodeview=(TextView)rv.findViewById(R.id.nodeview);
            ImageButton prev=(ImageButton)rv.findViewById(R.id.flechaprev);
            ImageButton next=(ImageButton)rv.findViewById(R.id.flechanext);
            final ViewSwitcher vs=(ViewSwitcher)rv.findViewById(R.id.viewSwitcher);
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vs.showNext();
                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vs.showNext();
                }
            });
            ui.init();
            Emat.init(ui.tabla);
            List.init();
            NetList.init();
            //Elementos que no pueden salir a UI porque tienen control de Activity
            Button test=(Button)rv.findViewById(R.id.test);
            Button bload=(Button)rv.findViewById(R.id.bload);
            Button bsave=(Button)rv.findViewById(R.id.bsave);
            bload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SimpleFileDialog.SimpleFileDialogListener dl=new SimpleFileDialog.SimpleFileDialogListener() {
                        @Override
                        public void onChosenDir(String chosenDir) {
                            Save.load(new File(chosenDir));
                        }
                    };
                    SimpleFileDialog FileSaveDialog = new SimpleFileDialog(v.getContext(),"FileOpen",dl);
                    FileSaveDialog.chooseFile_or_Dir();
                }
            });
            bsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SimpleFileDialog.SimpleFileDialogListener dl=new SimpleFileDialog.SimpleFileDialogListener() {
                        @Override
                        public void onChosenDir(String chosenDir) {
                            Save.save(new File(chosenDir));
                        }
                    };
                    SimpleFileDialog FileSaveDialog = new SimpleFileDialog(v.getContext(),"FileSave",dl);
                    FileSaveDialog.Default_File_Name = "circuit.icf";
                    FileSaveDialog.chooseFile_or_Dir();
                }
            });
            return rv;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((ItzaCircuit) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}

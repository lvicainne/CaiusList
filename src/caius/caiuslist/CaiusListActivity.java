package caius.caiuslist;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

public class CaiusListActivity extends Activity {
   
    private Button addPerson = null;
    private Button addTask = null;
    private Menu m = null;
    private Resources res = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        addPerson = (Button) findViewById(R.id.add_person);
        addTask = (Button) findViewById(R.id.add_task);

        
        res = getResources();
        String chaine = res.getString(R.string.addPerson);
        String chaine2 = res.getString(R.string.addTask);
        addPerson.setText(chaine);
        addTask.setText(chaine2);
            

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        //R.menu.menu est l'id de notre menu
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    private final static int MENU_DESACTIVER = Menu.FIRST;
    private final static int MENU_RETOUR = Menu.FIRST + 1;
    @Override
    public void onCreateContextMenu(ContextMenu menu, View vue,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, vue, menuInfo);
        menu.add(Menu.NONE, MENU_DESACTIVER, Menu.NONE, "Supprimer cet élément");
        menu.add(Menu.NONE, MENU_RETOUR, Menu.NONE, "Retour");
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_DESACTIVER:
            //item.getMenuInfo().targetView.setEnabled(false);

            case MENU_RETOUR:
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
package caius.caiuslist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.ToggleButton;

import android.widget.Toast;

public class CaiusListActivity extends Activity {
	//private RepertoireAdapter mAdapter = null;
	private TableLayout mListView = null;
   
    private Button addPerson = null;
    private Button addTask = null;
    private Menu m = null;
    private Resources res = null;
    
	//Identifiants pour les bo�tes de dialogue
	private final static int DIALOG_ADD = 0;
	private final static int DIALOG_MULTIPLE_DELETE = 1;
	private final static int DIALOG_MODIFY = 2;

	private Person currentContact = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        res = getResources();
        res.getLayout(R.layout.main);
        mAdapter = new RepertoireAdapter();
        
        mListView = getContentView();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long id) {
				Contact c = (Contact)mAdapter.getItem(position);
				//On passe de "s�lectionn�" � "d�s�lectionn�" ou l'inverse
				c.switchSelected();
				//On indique qu'on a chang� les informations
				mAdapter.notifyDataSetChanged();
			}
		});
        //Ajouter un menu contextuel � chaque item de la liste
        registerForContextMenu(mListView);
            

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
        menu.add(Menu.NONE, MENU_DESACTIVER, Menu.NONE, "Supprimer cet �l�ment");
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
    
    @Override
    public Dialog onCreateDialog(int id)
    {
    	AlertDialog retour = null;
		AlertDialog.Builder builder = null;
		LayoutInflater inflater = getLayoutInflater();
    	/*switch(id)
    	{
    	case DIALOG_ADD:
    		final RelativeLayout layoutAdd = (RelativeLayout) inflater.inflate(R.layout.addmodifydialog, null);
    		builder = new AlertDialog.Builder(this)
    			.setView(layoutAdd)
    			.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Person c = new Person();
						//R�cup�rer les champs
						EditText nom = (EditText)layoutAdd.findViewById(R.edit.name);

						//On met les informations dans le nouveau contact
						c.setNom(nom.getText().toString().trim());
						//Si on avait pas d'adaptateur, on le cr�e
						if(mAdapter == null)
							mAdapter = new RepertoireAdapter();
						//On ajoute l'item et si la liste n'avait pas encore d'adaptateur, on lui attribut
						if(mAdapter.addItem(c) && getListAdapter() == null)
								setListAdapter(mAdapter);
					}
				})
				.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Fermer la bo�te de dialogue
						dismissDialog(DIALOG_ADD);
					}
				})
				.setTitle("Ajouter un nouvel utilisateur");
    		break;
    	case DIALOG_MULTIPLE_DELETE:
    		builder = new AlertDialog.Builder(this)
    			.setTitle("Supprimer ces utilisateurs ?")
    			.setMessage("�tes-vous certain de vouloir supprimer ces utilisateurs ?")
    			.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//R�cup�ration des contacts s�lectionn�s
						ArrayList<Integer> selected = mAdapter.getListeSelected();
						int position;
						//On parcourt en sens inverse la liste...
						for(int i = selected.size() ; i > 0 ; i--)
						{
							//position du i�me contact s�lectionn�
							position = selected.get(i - 1);
							//et on supprime le contact � cette position
							mAdapter.deleteItem(position);
						}
					}
				});
    		break;
    	case DIALOG_MODIFY:
    		final RelativeLayout layoutModifiy = (RelativeLayout) inflater.inflate(R.layout.addmodifydialog, null);
    		builder = new AlertDialog.Builder(this)
				.setView(layoutModifiy)
				.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Person c = new Person();
						//R�cup�ration des informations ins�r�es dans les champs
						EditText nom = (EditText)layoutModifiy.findViewById(R.edit.name);

						//On le ins�re dans le contact
						c.setNom(nom.getText().toString().trim());
						//Et remplacement du contact d�j� existant
						mAdapter.modifyItem(c, currentContact.hashCode());
					}
				})
				.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Fermer la bo�te de dialogue
						dismissDialog(DIALOG_MODIFY);
					}
				})
				.setTitle("Modifier un utilisateur");
    		break;
    			
    	}
		retour = builder.create();*/
		return retour;
    }
    
    @Override
    public void onPrepareDialog (int id, Dialog dialog)
    {
    	switch(id)
    	{
    	case DIALOG_ADD:
			EditText nom = (EditText)dialog.findViewById(R.edit.name);

			//Remise � z�ro des champs de la bo�te de dialogue
			nom.setText("");
			//On accorde le focus au premier champ
			nom.requestFocus();

			break;
    	case DIALOG_MODIFY:
    		EditText nomMod = (EditText)dialog.findViewById(R.edit.name);

			//Les champs de la bo�te sont compl�t�s avec les informations sur le contadt
			nomMod.setText(currentContact.getNom());
			//On accorde le focus au premier champ
			nomMod.requestFocus();

			break;
    	}
    }
}
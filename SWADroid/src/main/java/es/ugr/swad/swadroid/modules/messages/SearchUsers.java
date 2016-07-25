package es.ugr.swad.swadroid.modules.messages;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import es.ugr.swad.swadroid.R;
import es.ugr.swad.swadroid.modules.Module;
import es.ugr.swad.swadroid.modules.courses.Courses;

/**
 * Created by Romilgildo on 17/07/2016.
 */
public class SearchUsers extends Module implements SearchView.OnQueryTextListener {
    private SearchView searchView;
    private MenuItem searchItem;
    private static ListView lvUsers;
    private String[] receivers = {};
    private String search;
    private UsersAdapter adapter;
    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_items);
        setTitle(R.string.actionBarAddUser);

        //users list
        lvUsers = (ListView) findViewById(R.id.listItems);
        adapter = new UsersAdapter(getBaseContext(), UsersRepository.getInstance().getUsers());
        lvUsers.setAdapter(adapter);
        lvUsers.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        //checkbox is checked when the row of an user is clicked
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                checkbox = (CheckBox) view.findViewById(R.id.check);
                if (checkbox.isChecked())
                    checkbox.setChecked(false);
                else
                    checkbox.setChecked(true);
                String idUser = UsersRepository.getInstance().getUsers().get(position).getUserID();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_users_bar, menu);

        searchItem = menu.findItem(R.id.action_search_field);
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener(){
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do whatever you need
                return true; // KEEP IT TO TRUE OR IT DOESN'T OPEN !!
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                NavUtils.navigateUpFromSameTask(SearchUsers.this);
                return true; // OR FALSE IF YOU DIDN'T WANT IT TO CLOSE!
            }
        });

        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint(getText(R.string.search_user));
        // listener to searchview
        searchView.setOnQueryTextListener(this);

        // searview expanded
        searchItem.expandActionView();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                if(!search.equals(""))
                onQueryTextSubmit(search);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public boolean onQueryTextSubmit(String query) {
        if (Courses.getSelectedCourseCode() != -1){
            showDialogSearch();
        }
        else{
            findUsers(-1);
        }

        //remove virtual keyboard
        //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search = newText;

        return true;
    }

    @Override
    protected void requestService() throws Exception {
    }

    @Override
    protected void connect() {

    }

    @Override
    protected void postConnect() {

    }

    @Override
    protected void onError() {

    }

    private void showDialogSearch(){
        int selected = 0; // does not select anything
        final String[] choiceList = {getString(R.string.in_subject) + " " + Courses.getSelectedCourseShortName(), getString(R.string.inAllPlatform)};

        AlertDialog.Builder builder = new AlertDialog.Builder(SearchUsers.this);
        builder.setTitle(R.string.where_to_search);
        builder.setCancelable(false);

        builder.setSingleChoiceItems(choiceList, selected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
            }
        });

        builder.setNegativeButton(getString(R.string.cancelMsg), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // no need to write anything here just implement this interface into this button
            }
        });

        builder.setPositiveButton(getString(R.string.acceptMsg), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                findUsers(Courses.getSelectedCourseCode());
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void findUsers(long codeSubject){
        String[] foundUsers = {search};
        adapter = new UsersAdapter(getBaseContext(), UsersRepository.getInstance().getUsers());
        lvUsers.setAdapter(adapter);

        //message about found users
        Toast.makeText(SearchUsers.this, R.string.users_found, Toast.LENGTH_SHORT).show();

        searchView.clearFocus();
    }

    /*
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }*/
}

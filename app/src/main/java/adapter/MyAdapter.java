package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.contacts.assignment.sunny.contactlist.Details;
import app.contacts.assignment.sunny.contactlist.R;
import pojo.Persons;
import app.contacts.assignment.sunny.contactlist.RoundedImageView;

/**
 * Created by sunny on 24/10/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Persons> person_object;
Context context;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        private RoundedImageView mImage;
        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.tv_label);
            mImage =(RoundedImageView) itemView.findViewById(R.id.rounded_iv_profile);

        }
    }



    public MyAdapter(ArrayList<Persons> contact_persons, Context context) {
        person_object = contact_persons;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = (person_object.get(position)).getName();
final String id= (person_object.get(position)).getContact_id();
        final String URI = (person_object.get(position)).getProfilePic();

        holder.txtHeader.setText(name);

    try
    {
        holder.mImage.setImageURI(Uri.parse(URI));
    }catch (Exception e)
    {
        e.printStackTrace();
        holder.mImage.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.person));

    }


        //holder.mImage.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.icon));
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(context,app.contacts.assignment.sunny.contactlist.Details.class);
                myIntent.putExtra("contact_id", id);
                myIntent.putExtra("contact_name", name);
                myIntent.putExtra("contact_uri", URI);
                //Optional parameters
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);
            }
        });
}

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return person_object.size();
    }

}
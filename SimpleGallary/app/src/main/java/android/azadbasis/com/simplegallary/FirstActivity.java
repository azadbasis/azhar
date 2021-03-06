package android.azadbasis.com.simplegallary;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {


    GridView gv;
    ArrayList<File> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        gv=(GridView)findViewById(R.id.gridView);
        gv.setAdapter(new GridAdapter());

        list=imageReader(Environment.getExternalStorageDirectory());

    }

    class  GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView=getLayoutInflater().inflate(R.layout.single_grid, parent, false);
            ImageView iv= (ImageView) convertView.findViewById(R.id.imageView);


            iv.setImageURI(Uri.parse(getItem(position).toString()));

            return convertView;
        }


    }
    ArrayList<File> imageReader(File root){

        ArrayList<File> a=new ArrayList<>();

        File[] files=root.listFiles();
        for(int i=0;i<files.length;i++){
            if(files[i].isDirectory()){
                a.addAll(imageReader(files[i]));
            }
            else {

                if(files[i].getName().endsWith("jpg")){
                    a.add(files[i]);
                }
            }
        }
        return a;
    }
}

package khoattv.retrofithw.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import khoattv.retrofithw.R;
import khoattv.retrofithw.networks.NetworksService;
import khoattv.retrofithw.networks.Response;
import khoattv.retrofithw.networks.RetrofitFactory;
import khoattv.retrofithw.networks.Task;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {

  private String userToken;
  private ListView lvTask;
  private List<Task> tasks;

  public TaskFragment() {
    // Required empty public constructor
  }

  public TaskFragment setUserToken(String userToken) {
    this.userToken = userToken;
    return this;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    tasks = new ArrayList<>();
    View view = inflater.inflate(R.layout.fragment_task, container, false);
    lvTask = (ListView) view.findViewById(R.id.lv_task);
    loadData();
    return view;
  }

  private void loadData() {
    NetworksService networksService = RetrofitFactory.getInstance().createService(NetworksService.class);
    networksService.getAllTask("JWT " + userToken).enqueue(new Callback<List<Task>>() {
      @Override
      public void onResponse(Call<List<Task>> call, retrofit2.Response<List<Task>> response) {
        Log.d("khoa", response.code() + "");
        if (response.code() == 200) {
          tasks = response.body();
          for (Task task: tasks) {
            Log.d("khoa", task.toString());
          }
          lvTask.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
              return tasks.size();
            }

            @Override
            public Object getItem(int position) {
              return tasks.get(position);
            }

            @Override
            public long getItemId(int position) {
              return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
              if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                convertView = layoutInflater.inflate(R.layout.item_task, parent, false);
              }
              Task task = tasks.get(position);
              TextView tvDate = (TextView) convertView.findViewById(R.id.tv_date);
              tvDate.setText(task.getDue_date());
              TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
              tvName.setText(task.getName());
              TextView tvPayment = (TextView) convertView.findViewById(R.id.tv_payment_per_hour);
              tvPayment.setText(String.valueOf(task.getPayment_per_hour()));
              CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
              checkBox.setChecked(task.isDone());
              return convertView;
            }
          });
        }
      }

      @Override
      public void onFailure(Call<List<Task>> call, Throwable t) {
        Toast.makeText(getActivity(), "No connection!", Toast.LENGTH_SHORT).show();
      }
    });


  }
}

package com.jiaqi.busy2lazy;

import java.util.ArrayList;

import com.jiaqi.busy2lazy.adapter.CellListAdapter;
import com.jiaqi.busy2lazy.adapter.ViewHolder;
import com.jiaqi.busy2lazy.model.CellInfo;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class EditCellActivity extends Activity {

	private ListView lv;
	private CellListAdapter mAdapter;
	private ArrayList<CellInfo> list;
	private Button bt_selectall;
	private Button bt_cancel;
	private Button bt_deselectall;
	private int checkNum; // ��¼ѡ�е���Ŀ����
	private TextView tv_show;// ������ʾѡ�е���Ŀ����

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_cell);
		
		list = (ArrayList<CellInfo>) getIntent().getSerializableExtra("CELLS");
		
		/* ʵ���������ؼ� */
		lv = (ListView) findViewById(R.id.lv);
		bt_selectall = (Button) findViewById(R.id.bt_selectall);
		bt_cancel = (Button) findViewById(R.id.bt_cancelselectall);
		bt_deselectall = (Button) findViewById(R.id.bt_deselectall);
		tv_show = (TextView) findViewById(R.id.tv);
		//list = new ArrayList<CellInfo>();
		// ΪAdapter׼������
		//initDate();
		// ʵ�����Զ����MyAdapter
		mAdapter = new CellListAdapter(list, this);
		// ��Adapter
		lv.setAdapter(mAdapter);

		// ȫѡ��ť�Ļص��ӿ�
		bt_selectall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ����list�ĳ��ȣ���MyAdapter�е�mapֵȫ����Ϊtrue
				for (int i = 0; i < list.size(); i++) {
					CellListAdapter.getIsSelected().put(i, true);
				}
				// ������Ϊlist�ĳ���
				checkNum = list.size();
				// ˢ��listview��TextView����ʾ
				dataChanged();
			}
		});
		// ȡ����ť�Ļص��ӿ�
		bt_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ����list�ĳ��ȣ�����ѡ�İ�ť��Ϊδѡ
				for (int i = 0; i < list.size(); i++) {
					if (CellListAdapter.getIsSelected().get(i)) {
						CellListAdapter.getIsSelected().put(i, false);
						checkNum--;// ������1
					}
				}
				// ˢ��listview��TextView����ʾ
				dataChanged();

			}
		});

		// ��ѡ��ť�Ļص��ӿ�
		bt_deselectall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ����list�ĳ��ȣ�����ѡ����Ϊδѡ��δѡ����Ϊ��ѡ
				for (int i = 0; i < list.size(); i++) {
					if (CellListAdapter.getIsSelected().get(i)) {
						CellListAdapter.getIsSelected().put(i, false);
						checkNum--;
					} else {
						CellListAdapter.getIsSelected().put(i, true);
						checkNum++;
					}

				}
				// ˢ��listview��TextView����ʾ
				dataChanged();
			}
		});

		// ��listView�ļ�����
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// ȡ��ViewHolder����������ʡȥ��ͨ������findViewByIdȥʵ����������Ҫ��cbʵ���Ĳ���
				ViewHolder holder = (ViewHolder) arg1.getTag();
				// �ı�CheckBox��״̬
				holder.cb.toggle();
				// ��CheckBox��ѡ��״����¼����
				CellListAdapter.getIsSelected().put(arg2, holder.cb.isChecked());
				// ����ѡ����Ŀ
				if (holder.cb.isChecked() == true) {
					checkNum++;
				} else {
					checkNum--;
				}
				// ��TextView��ʾ
				tv_show.setText("��ѡ��" + checkNum + "��");

			}
		});
	}

	// ��ʼ������
	private void initDate() {

		// list.add("data" + "   " + i);
		for (int i = 1; i <= 5; i++) {
			list.add(new CellInfo());
		}
		for (CellInfo c : list) {
			c.lac = (int) (Math.random() * 100);
			c.cid = (int) (Math.random() * 1000);
		}

	}

	// ˢ��listview��TextView����ʾ
	private void dataChanged() {
		// ֪ͨlistViewˢ��
		mAdapter.notifyDataSetChanged();
		// TextView��ʾ���µ�ѡ����Ŀ
		tv_show.setText("��ѡ��" + checkNum + "��");
	}

}

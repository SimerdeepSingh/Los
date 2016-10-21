package los.valiance.com.los.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import los.valiance.com.los.Model.LeadDetails;
import los.valiance.com.los.R;

/**
 * Created by admin2 on 20-10-2016.
 */

public class LeadDetailsAdapter extends BaseAdapter implements Filterable {
    Context context;
    private List<LeadDetails> list;
    ValueFilter valueFilter;
    List<LeadDetails> mStringFilterList;
    TextView loanId,applicantName,loanPurposeType,loanType;
    public LeadDetailsAdapter(Context context, List<LeadDetails> list) {
        this.context = context;
        this.list = list;
        this.mStringFilterList = list;
    }

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
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.viewleads, null);
        }
        loanId= (TextView) view.findViewById(R.id.loanid);
        applicantName= (TextView) view.findViewById(R.id.applicantname);
        loanPurposeType= (TextView) view.findViewById(R.id.loanpurposetype);
        loanType= (TextView) view.findViewById(R.id.loantype);

        loanId.setText(String.valueOf(list.get(position).getLeadId()));
        applicantName.setText(String.valueOf(list.get(position).getFirstName()));
        loanPurposeType.setText(String.valueOf(list.get(position).getLoanPurposeType()));
        loanType.setText(String.valueOf(list.get(position).getLoanType()));
        return view;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<LeadDetails> filterList = new ArrayList<>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getFirstName().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
                Log.i("asasas", String.valueOf(results.values));
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
           list = (ArrayList<LeadDetails>) results.values;
           if (list.size() != 0)
                notifyDataSetChanged();
        }
    }
}

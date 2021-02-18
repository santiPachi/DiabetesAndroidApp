package com.example.diabetes.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Modelo.Glicemia;
import com.example.diabetes.R;
import com.example.diabetes.Singleton;
import com.example.diabetes.utils.*;
import java.util.ArrayList;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;


public class GraficasActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    TextView tvMedico;
    TextView tvAccion;
    TextView tvFecha;
    TextView tvVariacion;

    Button btHistorialGlicemiaAyuna;
    Button btHistorialGlicemiaAlmuerzo;
    Button btHistorialGlicemiaDesayuno;
    Button btHistorialGlicemiaMerienda;
    Button btRevisar;
    public BarChart barCharGlicemiaAyuna;
    public BarChart barCharGlicemiaDesayuno;
    public BarChart barCharGlicemiaAlmuerzo;
    public BarChart barCharGlicemiaMerienda;
    private PieChart chartAlimentacion;
    MediatorHistorial mediatorHistorial;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficas);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.mediatorHistorial = new MediatorHistorial(this);
        this.mediatorHistorial.notificar("GraficarControles");
        String medico = getIntent().getStringExtra("Doctor");
        String descripcion = getIntent().getStringExtra("Descripcion");
        String fecha = getIntent().getStringExtra("Fecha");
        String accion = getIntent().getStringExtra("Acción");
        String variacion = getIntent().getStringExtra("Variación");

        btHistorialGlicemiaAyuna = findViewById(R.id.bt_historial_glicimia_ayunas);
        btHistorialGlicemiaDesayuno = findViewById(R.id.bt_historial_glicimia_desayuno);
        btHistorialGlicemiaAlmuerzo = findViewById(R.id.bt_historial_glicimia_almuerzo);
        btHistorialGlicemiaMerienda = findViewById(R.id.bt_historial_glicimia_merienda);

        btRevisar = findViewById(R.id.bt_revisar_control);
        if(Singleton.verify.getTipoUsuario().equals("doctor")){
            btRevisar.setVisibility(View.VISIBLE);
        }
        tvAccion = findViewById(R.id.tx_accion_hist_glic);
        tvFecha = findViewById(R.id.tx_fecha_hist_glic);
        tvMedico = findViewById(R.id.tx_medico_hist_glic);
        tvVariacion = findViewById(R.id.tx_variacion_hist_glic);
        tvVariacion.setText(variacion);
        tvMedico.setText(medico);
        tvFecha.setText(fecha);
        tvAccion.setText(accion);


        setChartAlimentacion();

        btHistorialGlicemiaAyuna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mediatorHistorial.notificar("HistorialGlicemias");
            }
        });
        btRevisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), RevisarControl.class);
                startActivity(intent);
            }
        });
    }
    public BarChart setChartGlicemia(ArrayList<Glicemia> lista, int id,String tipo){
        BarChart chart ;
        chart = findViewById(id);
        chart.setOnChartValueSelectedListener(this);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        chart.setDrawValueAboveBar(false);
        chart.setHighlightFullBarEnabled(false);


        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        // change the position of the y-labels
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new MyValueFormatter("mg/dl"));
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        chart.getAxisRight().setEnabled(false);

        XAxis xLabels = chart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);

        // chart.setDrawXLabels(false);
        // chart.setDrawYLabels(false);



        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);
        ArrayList<BarEntry> values = new ArrayList<BarEntry>();
        int i =0;
        for (Glicemia glicemia: lista){

            float rojo = 0;
            float verde = 0;
            float amarillo = 0;

            if (glicemia.getNivelGlucosa() <52 || glicemia.getNivelGlucosa() > 200){
                rojo = glicemia.getNivelGlucosa();
            }else if (glicemia.getNivelGlucosa() < 70 || glicemia.getNivelGlucosa() > 140){
                amarillo = glicemia.getNivelGlucosa();
            }else{
                verde = glicemia.getNivelGlucosa();
            }
            if (tipo.equals("ayuno") && glicemia.getAyunas()==1){
                values.add(new BarEntry(
                        i,
                        new float[]{verde, amarillo, rojo}));
                i ++;
            }else if (tipo.equals("desayuno") && glicemia.getDesayuno()==1){
                values.add(new BarEntry(
                        i,
                        new float[]{verde, amarillo, rojo}));
                i ++;
            }else if (tipo.equals("almuerzo") && glicemia.getAlmuerzo()==1){
                values.add(new BarEntry(
                        i,
                        new float[]{verde, amarillo, rojo}));
                i ++;
            }else if (tipo.equals("merienda") && glicemia.getMerienda()==1){
                values.add(new BarEntry(
                        i,
                        new float[]{verde, amarillo, rojo}));
                i ++;
            }

        }


        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "");
            set1.setDrawIcons(false);
            set1.setColors(getColors());
            set1.setStackLabels(new String[]{"Normal", "Regular", "Urgente"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueFormatter(new StackedValueFormatter(false, "", 1));
            data.setValueTextColor(Color.WHITE);

            chart.setData(data);
        }

        chart.setFitBars(true);
        chart.invalidate();

        // add a nice and smooth animation
        chart.animateY(700);
        return chart;
    }
    public void setChartAlimentacion(){
        chartAlimentacion = findViewById(R.id.chart_alimentacion);
        chartAlimentacion.setUsePercentValues(true);
        chartAlimentacion.getDescription().setEnabled(false);
        chartAlimentacion.setExtraOffsets(5, 10, 5, 5);

        chartAlimentacion.setDragDecelerationFrictionCoef(0.95f);


        chartAlimentacion.setCenterText(generateCenterSpannableText());

        chartAlimentacion.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        chartAlimentacion.setDrawHoleEnabled(true);
        chartAlimentacion.setHoleColor(Color.WHITE);

        chartAlimentacion.setTransparentCircleColor(Color.WHITE);
        chartAlimentacion.setTransparentCircleAlpha(110);

        chartAlimentacion.setHoleRadius(58f);
        chartAlimentacion.setTransparentCircleRadius(61f);

        chartAlimentacion.setDrawCenterText(true);

        chartAlimentacion.setRotationAngle(0);
        // enable rotation of the chart by touch
        chartAlimentacion.setRotationEnabled(true);
        chartAlimentacion.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        chartAlimentacion.setOnChartValueSelectedListener(this);

        ;

        chartAlimentacion.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chartAlimentacion.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
        setData(4, 10);

    }
    protected final String[] months = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected final String[] parties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };
    private int[] getColors() {

        // have as many colors as stack-values per entry
        int[] colors = new int[3];

        System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 3);

        return colors;
    }

    private void setData(int count, float range) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) (Math.random() * range) + range / 5, parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);


        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        //dataSet.setUsingSliceColorAsValueLineColor(true);

        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        chartAlimentacion.setData(data);

        // undo all highlights
        chartAlimentacion.highlightValues(null);

        chartAlimentacion.invalidate();
    }






    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.5f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.65f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", xIndex: " + e.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}

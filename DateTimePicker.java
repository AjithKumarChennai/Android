int selectedYear, selectedMonth, selectedDate, hour, min;



etDatePicker = findViewById(R.id.etDatePicker);
        etDatePicker.setFocusable(false);

        etTimePicker = findViewById(R.id.etTimePicker);
        etTimePicker.setFocusable(false);

        Calendar calendar = Calendar.getInstance();

        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);
        selectedDate = calendar.get(Calendar.DAY_OF_MONTH);

        hour = calendar.get(Calendar.HOUR);
        min = calendar.get(Calendar.MINUTE);
        
        
        
etDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        selectedYear = i;
                        selectedMonth = i1;
                        selectedDate = i2;

                        etDatePicker.setText((i1+1) + "/" + i2 + "/" + i);

                    }
                }, selectedYear, selectedMonth, selectedDate);

                datePickerDialog.show();

            }
        });



        etTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        hour = i;

                        min = i1;

                        String AM_PM = hour >= 12 ? "PM" : "AM";

                        if(hour > 12){

                            hour = hour - 12;

                        }

                        etTimePicker.setText(((hour < 10) ? "0" : "") + hour + " : " + ((min < 10) ? "0" : "") + min + " " + AM_PM);

                    }
                }, hour, min, false);

                timePickerDialog.show();

            }
        });

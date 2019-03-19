Calendar current = Calendar.getInstance();

            int currentDate = current.get(Calendar.DAY_OF_MONTH);

            int currentMonth = current.get(Calendar.MONTH);

            int currentYear = current.get(Calendar.YEAR);

            Calendar beforeFiveYears = Calendar.getInstance();

            beforeFiveYears.set(Calendar.DAY_OF_MONTH, currentDate);

            beforeFiveYears.set(Calendar.MONTH, currentMonth);

            beforeFiveYears.set(Calendar.YEAR, currentYear - 5);

            long difference = current.getTimeInMillis() - beforeFiveYears.getTimeInMillis();

            long days = difference / (24 * 60 * 60 * 1000);

            int previousYears = Math.round(days) + 1;

            int nextYears = Math.round(days) - 1;

            Calendar saturday;

            Calendar sunday;

            List<Calendar> weekends = new ArrayList<>();

            for (int j = previousYears; j > 0; j = j - 7) {

                sunday = Calendar.getInstance();

                sunday.add(Calendar.DAY_OF_YEAR, (Calendar.SUNDAY - sunday.get(Calendar.DAY_OF_WEEK) + 7 - j));

                saturday = Calendar.getInstance();

                saturday.add(Calendar.DAY_OF_YEAR, (Calendar.SATURDAY - saturday.get(Calendar.DAY_OF_WEEK) - j));

                weekends.add(saturday);

                weekends.add(sunday);
            }

            for (int i = 0; i < nextYears; i = i + 7) {

                sunday = Calendar.getInstance();

                sunday.add(Calendar.DAY_OF_YEAR, (Calendar.SUNDAY - sunday.get(Calendar.DAY_OF_WEEK) + 7 + i));

                saturday = Calendar.getInstance();

                saturday.add(Calendar.DAY_OF_YEAR, (Calendar.SATURDAY - saturday.get(Calendar.DAY_OF_WEEK) + i));

                weekends.add(saturday);

                weekends.add(sunday);

            }

            Calendar[] disabledDays = weekends.toArray(new Calendar[weekends.size()]);

            dpdEndDate.setDisabledDays(disabledDays);

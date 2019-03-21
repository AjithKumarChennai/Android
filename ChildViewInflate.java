if (UtilsClass.isValidString(whatsNew.get(position).get(i))) {

                    View childViewNewUpdate = ((Activity) mContext).getLayoutInflater().inflate(R.layout.new_update_layout, null);

                    childViewNew.addView(childViewNewUpdate);

                    AnyTextView newVersionDescText01 = childViewNewUpdate.findViewById(R.id.newVersionDescText01);

                    newVersionDescText01.setText(whatsNew.get(position).get(i));

                }

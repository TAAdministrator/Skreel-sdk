# How to use the Skreel SDK

[![](https://jitpack.io/v/skreeladmin/Skreel-sdk.svg)](https://jitpack.io/#skreeladmin/Skreel-sdk)

Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.skreeladmin:Skreel-sdk:Tag'
	}
	

Add the SkreelSDK class to your code, 

Step 3. Initialize the class

        @Override
        public void onCreate() {
            super.onCreate();
            SkreelSDK.getInstance(this);
        }
        
        

Step 4. Call the required method and implement the necessary interfaces

A Customer creation sample

    SkreelSDK.createCustomer("07032068837", new CustomerCreatedListener() {
                @Override
                public void onCustomerCreated(Customer customer) {
                    Log.d(TAG, "onCustomerCreated: " + customer);
                }
    
                @Override
                public void onFailure(Meta meta) {
                    Log.d(TAG, "onFailure: " + meta);
                }
            });

Create a card sample

        Button button = findViewById(R.id.click_me);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkreelSDK.displayCardView(MainActivity.this,"0800186ed9f143c48ed628f0db241a7f",5);
            }
        });


Get Card creation result
 
       @Override
       protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
           super.onActivityResult(requestCode, resultCode, data);
           if(requestCode == 5){
               if(resultCode == Activity.RESULT_OK){
                   Card ca = (Card)data.getSerializableExtra("card");
                   }
               }
           }

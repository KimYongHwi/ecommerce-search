import React from 'react';
import Banner from '../components/Banner';
import NewArrival from '../components/NewArrival';
import deliverVan from '../assets/images/delivery-van.svg';
import moneyBack from '../assets/images/money-back.svg';
import serviceHours from '../assets/images/service-hours.svg';

function Main() {
  return (
    <div>
      <div class='container mx-auto'>
        <Banner />
      </div>

      <div class='container py-16'>
        <div class='w-10/12 grid grid-cols-1 md:grid-cols-3 gap-6 mx-auto justify-center'>
          <div class='border border-primary rounded-sm px-3 py-6 flex justify-center items-center gap-5'>
            <img src={deliverVan} alt='Delivery' class='w-12 h-12 object-contain' />
            <div>
              <h4 class='font-medium capitalize text-lg'>Free Shipping</h4>
              <p class='text-gray-500 text-sm'>Order over $200</p>
            </div>
          </div>
          <div class='border border-primary rounded-sm px-3 py-6 flex justify-center items-center gap-5'>
            <img src={moneyBack} alt='Delivery' class='w-12 h-12 object-contain' />
            <div>
              <h4 class='font-medium capitalize text-lg'>Money Rturns</h4>
              <p class='text-gray-500 text-sm'>30 days money returs</p>
            </div>
          </div>
          <div class='border border-primary rounded-sm px-3 py-6 flex justify-center items-center gap-5'>
            <img src={serviceHours} alt='Delivery' class='w-12 h-12 object-contain' />
            <div>
              <h4 class='font-medium capitalize text-lg'>24/7 Support</h4>
              <p class='text-gray-500 text-sm'>Customer support</p>
            </div>
          </div>
        </div>
      </div>

      <NewArrival />
    </div>
  );
}

export default Main;

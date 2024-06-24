import React from 'react';
import bannerBg from '../assets/images/banner-bg.jpg';

function Banner() {
  return (
    <div
      class='bg-cover bg-no-repeat bg-center py-36'
      style={{
        backgroundImage: `url(${bannerBg})`,
      }}
    >
      <div class='container px-20'>
        <h1 class='text-6xl text-gray-800 font-medium mb-4 capitalize'>
          best collection for <br /> home decoration
        </h1>
        <p>
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Aperiam <br />
          accusantium perspiciatis, sapiente magni eos dolorum ex quos dolores odio
        </p>
        <div class='mt-12'>
          <a
            href='#'
            class='bg-primary border border-primary text-white px-8 py-3 font-medium 
                    rounded-md hover:bg-transparent hover:text-primary'
          >
            Shop Now
          </a>
        </div>
      </div>
    </div>
  );
}

export default Banner;

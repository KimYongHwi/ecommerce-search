# Ecommerce-Search
- 해당 repository는 상품 정보를 검색하는 전반적인 기능을 담당합니다.

## 1. Prerequisites
- 상품 정보는 [ashraq/fashion-product-images-small](https://huggingface.co/datasets/ashraq/fashion-product-images-small)를 사용합니다.
- 상품의 이미지 정보는 [fashion-product-images-dataset의 images.csv](https://www.kaggle.com/datasets/paramaggarwal/fashion-product-images-dataset?resource=download)을 사용합니다.
  - images.csv를 다운받아서 model-api 디렉토리에 추가해주세요.
- 전체 상품 수는 44,072개
- 주피터 노트북을 이용해 상품 정보 관련 CSV 파일을 생성해주세요.
- [save_products_csv.ipynb](https://github.com/KimYongHwi/ecommerce-search/blob/main/model-api/save_products_csv.ipynb)
- docker-compose.yaml을 실행시켜주세요.
  ```
  $ docker-compose up -d
  ```
- http://localhost:5601/app/dev_tools#/console 접속 후 `products` index를 만들어주세요.
    ```
    PUT products
    {
      "mappings": {
        "properties": {
          "productNo": {
            "type": "long"
          },
          "productDisplayName": {
            "type": "text"
          },
          "gender": {
            "type": "text"
          },
          "mainCategory": {
            "type": "text"
          },
          "subCategory": {
            "type": "text"
          },
          "articleType": {
            "type": "text"
          },
          "baseColor": {
            "type": "text"
          },
          "season": {
            "type": "text"
          },
          "year": {
            "type": "date"
          },
          "usage": {
            "type": "text"
          },
          "image": {
            "type": "text",
            "index": false
          },
          "searchKeywords": {
            "type": "text"
          },
          "image_features": {
            "type": "knn_vector",
            "dimension": 512,
            "method": {
              "name": "hnsw",
              "space_type": "l2",
              "engine": "lucene",
              "parameters": {
                "ef_construction": 128,
                "m": 24
              }
            }
          },
          "text_features": {
            "type": "knn_vector",
            "dimension": 1024,
            "method": {
              "name": "hnsw",
              "space_type": "l2",
              "engine": "lucene",
              "parameters": {
                "ef_construction": 128,
                "m": 24
              }
            }
          }
        }
      },
      "settings": {
        "index": {
          "knn": true,
          "knn.algo_param.ef_search": 100,
          "refresh_interval": "10s",
          "number_of_replicas": "10",
          "number_of_shards": "5"
        }
      }
    }
    ```


## 2. 색인
1. model-api를 실행시켜주세요
- model-api는 이미지와 텍스트에 대한 feature를 제공하는 역할을 합니다.
  ```
  $ cd model-api
  $ pip install fastapi # fastapi cli 설치
  $ fastapi dev main.py # fastapi 실행
  ```

2. api module을 실행시켜주세요.
- api module은 색인 및 검색 기능을 제공합니다.
- `EcommerceSearchApplication` 실행

3. publisher module을 실행시켜주세요.
- publisher module을 csv를 읽어 kafka로 메시지를 발행하는 역할을 합니다.
- `BootPublisherApplication` 실행

4. 색인 결과를 확인해주세요.
- http://localhost:5601/app/dev_tools#/console 접속
  ```
  GET products/_count
  ```

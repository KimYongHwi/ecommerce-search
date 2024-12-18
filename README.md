# Ecommerce-Search
- Semantic 검색을 위한 검색 시스템 토이 프로젝트

## 아키텍처
![diagram-export-2024 -12 -18 -오후-6_01_28](https://github.com/user-attachments/assets/a9b8c6be-1f54-408e-97db-761282304160)

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
<details>
  <summary>mapping</summary>
  
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
</details>


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

## 3. Api
1. Api를 실행시켜주세요.
- `EcommerceSearchApplication` 실행

2. Swagger
- http://localhost:8080/swagger-ui/index.html
  
## 4. Web
| [ecommerce-template-tailwind-1](https://github.com/fajar7xx/ecommerce-template-tailwind-1) 을 참고해서 개발했습니다.

1. Web을 실행시켜주세요.
```
$ cd web
$ npm run start
```

2. Main Page
<img width="1728" alt="스크린샷 2024-07-07 오후 6 06 19" src="https://github.com/KimYongHwi/ecommerce-search/assets/44759868/f8424320-8fa7-466c-a11a-7bbb3b114909">


## Embedding 모델 비교

| 검색어 | laser  | multilingual-e5-base | distiluse-base-multilingual-cased-v1 |
| -------------| ------------- | ------------- |------------- |
|protected notebook| Content Cell  | Content Cell  | Content Cell |


## 사용 기술
- model-api: python, FastAPI
  - embedding
    - [facebook/LASER](https://github.com/facebookresearch/LASER)
    - multilingual-e5-base
    - distiluse-base-multilingual-cased-v1
- backend-api: kotlin, Spring boot
- web: javascript, react.js
- infra
  - search engine: opensearch
  - queue: kafka
  - cache: redis

## TODO
- image search

/**
 * 인기영화 불러오는 js
 */
console.log("ggggg")

let willOpen =
	"https://api.themoviedb.org/3/movie/upcoming?api_key=b96ed5ac7fac8cd9c2670c50e891b392&language=ko-KR&page=1&region=KR"
let imgUrl = "https://image.tmdb.org/t/p/w185";
fetch(willOpen)
	.then((result) => result.json())
	.then(data => {
		console.log(data)


		// window.onload = function () {
		// let hello = document.getElementById('hello');

		// for (var i = 0; i > data["results"].length; i++) {
		//console.log(data["results"][1].poster_path)
		let imgtg = document.querySelector('div .product__item');//이미지테그달 곳 가져오기
		let div = document.createElement('div');
		div.element.className ='product__item__pic set-bg'
		imgtg.prepend(div)
		console.log(div)
		//this.div.setAttribute("src", imgUrl + data["results"][1].poster_path);
		
		
		//<div class="blog-item" style="background-image : url(이미지경로)">
			//<div class="product__item__pic set-bg" data-setbg="img/trending/trend-1.jpg">
				//img.setAttribute("src", imgUrl + data["results"][i].poster_path)

                    // let div = document.createElement('div');
                    //     let img = document.createElement('img');
                    //     let imgUrl= "https://image.tmdb.org/t/p/w185";
                    //  let img1 = img.setAttribute("src", imgUrl + data["results"][i].poster_path);
                    //  console.log(img1)



                    //   div.append(img);
                    //    hello.append(div)
                    //    }


                        //console.log(data["results"][i].title) //제목가져오는 것
                        //console.log(data["results"][i].poster_path) //사진가져오는 것
                   // }
                   // hello.append(div.append(img))
                

            })

            .catch(err => console.log(err))

requirejs.config({
    baseUrl: 'resources/js/plugins',
    paths: {
        jquery: 'jquery'
    }
});

define(["jquery", "../includes/util"], function() {

    var ITEMS_PER_PAGE = 13;
    var numberOfPages = 0;
    var currentPage = 1;
    var $productsCatalog = null;
    var category = null;
    var keywords = null;

    var productTemplate = '<div class="product">\
                            <div class="product-image"><img src="{0}"/></div>\
                            <div class="product-title">{1}</div>\
                            <div class="product-description">{2}</div>\
                            <div class="product-price">{3}</div>\
                           </div>';


    $(function() {
        init();
        setButtonsHandlers();
    });

    function init() {
        $productsCatalog = $('.products-catalog');
    }

    function setButtonsHandlers() {
        $('#search-products-btn').click(function() {
            category = $("#search-category").val();
            keywords = $("#keywords").val();
            currentPage = 1;
            searchAmazonProducts();
        });
    }

    function searchAmazonProducts() {

        $.ajax({
            url:'amazonProductSearch',
            dataType: 'json',
            type: 'POST',
            data: {
                searchIndex: category,
                keywords: keywords,
                currentPage: currentPage
            },
            success: function(response) {
                console.log("Server response: ", response);
                updateListOfProducts(response.ItemSearchResponse.Items.Item);
            }
        });
    }

    function updateListOfProducts(products) {
        $productsCatalog.empty();

        var currentProductHtml = null;
        var image = "noimage.jpg";
        var title = null;
        var description = "N/A";
        var price = "N/A";
        $.each(products, function (index, product) {
            currentProductHtml = productTemplate;
            image = product.LargeImage.URL;
            title = product.ItemAttributes.Title;
            description = product.ItemAttributes.Feature ? product.ItemAttributes.Feature.join() : "N/A";
            price = product.ItemAttributes.ListPrice != undefined ? product.ItemAttributes.ListPrice.FormattedPrice : "N/A";

            currentProductHtml = currentProductHtml.format(image, title, description, price);

            $productsCatalog.append(currentProductHtml);
        });
    }
});
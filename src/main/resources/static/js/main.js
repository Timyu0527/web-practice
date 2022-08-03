$(document).ready(function(){
    $('button').click(function(){
        let $sights = $('#content');
        let zone = $(this).text();
//        console.log(zone);
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/SightAPI?zone=' + zone,
            success: function(sights){
                $sights.html("");
                $.each(sights, function(i, sight){
                    let accordion= $("<div></div>").attr("id", "accordion");
                    let card = $("<div></div>").addClass("card");
                    let header = $("<div></div>").addClass("card-header").text(sight.sightName);
                    let body = $("<div></div>").addClass("card-body");
                    body.html("<p>地區：" + sight.zone + "</p>"
                                + "<p>分類：" + sight.category + "</p>");
                    let address = $("<a></a>").addClass("btn btn-primary")
                                              .attr("id", "address_button")
                                              .attr("href", "https://www.google.com.tw/maps/place/" + sight.address)
                                              .text("地址");

                    let descriptionHeader = $("<div></div>").addClass("card-header");
                    let descriptionTitle = $("<a></a>").addClass("btn")
                                                       .attr("data-bs-toggle", "collapse")
                                                       .attr("href", "#collapse" + i)
                                                       .text("詳細資訊");
                    descriptionHeader.append(descriptionTitle);

                    let descriptionCollapse = $("<div></div>").addClass("collapse")
                                                              .attr("id", "collapse" + i)
                                                              .attr("data-bs-parent", "#accordion");
                    let descriptionBody = $("<div></div>").addClass("card-body")
                                                          .text(sight.description);
                    descriptionCollapse.append(descriptionBody);

                    body.append(address, descriptionHeader, descriptionCollapse);
                    card.append(header, body);
                    accordion.append(card);
                    $sights.append(accordion);
                });
            }
        });
    });
})
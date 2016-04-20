/**
 * Created by milew on 23.2.2016.
 */
WPAngularStarter.factory('ListingService', ['$http', 'apiURL', function ($http, apiURL) {
    return {
        save: function (title, content, file) {
            var fd = new FormData();
            for (i = 0; i < file.length; i++)
                fd.append('file[]', file[i]);

            fd.append('title', title);
            fd.append('content', content);

            //return false;
            return $http({
                url: 'http://localhost:8080/servlet-showcase/api/listing/new',
                method: 'POST',
                headers: {'Content-Type': undefined},
                transformRequest: angular.identity,
                data: fd
            });
        },

        query: function () {
            return $http({
                url: 'http://localhost:8080/servlet-showcase/api/listing',
                method: 'GET'
            });
        },

        queryListing: function (id) {
            return $http({
                url: 'http://localhost:8080/servlet-showcase/api/listing/' + id,
                method: 'GET'
            });
        },

        delete: function (id) {
            return $http({
                url: 'http://localhost:8080/servlet-showcase/api/listing/' + id,
                method: 'DELETE'
            });
        },

        reportPost: function(report){
            var data = $.param({
                "content": report.content,
                "listingId": report.listingId
            });
            var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded'}};
            return $http.post(apiURL + "/listing/report/new", data, config);
        }
    }
}]);
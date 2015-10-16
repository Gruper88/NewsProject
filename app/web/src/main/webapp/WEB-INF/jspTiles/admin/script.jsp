<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- inter dinamic Category JQ-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function ($) {
        $('p.hide').hide();

        $('a.add_file').on('click', function (e) {
            $('p.hide:not(:visible):first').show('slow');
            if (!$('p.hide:not(:visible)').length) {
                $('a.add_file').hide(10);
            }
            e.preventDefault();
        });

        $('a.del_file').on('click', function (e) {
            var input_parent = $(this).parent();
            var input_wrap = input_parent.find('span');
            input_wrap.html(input_wrap.html());
            input_parent.hide('slow');
            if ($('a.add_file:not(:visible)').length) {
                $('a.add_file').show();
            }
            e.preventDefault();
        });
    });
</script>

<!-- inter data Calendar JQ-->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script>
    $(function () {
        $("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
    });
</script>

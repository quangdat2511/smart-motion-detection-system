<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="footer">
    <div class="footer-inner">
        <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">smart-motion-system</span>
						</span>

            &nbsp; &nbsp;
        </div>
    </div>
</div>
<script>
    setInterval(async () => {
        try {
            const res = await fetch('/api/check-session', {
                method: 'GET',
                credentials: 'same-origin' // rất quan trọng nếu dùng session
            });

            const data = await res.json();

            if (!data.authenticated) {
                // Có thể redirect về login kèm thông báo hết phiên
                window.location.href = '/login?sessionExpired=true';
            }
        } catch (e) {
            // Trường hợp bị lỗi (mất mạng, timeout...) thì cũng logout
            window.location.href = '/login?sessionExpired=true';
        }
    }, 1000);
</script>

</body>
</html>